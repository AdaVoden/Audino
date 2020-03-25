package Audino.MediaControl;

import Audino.MediaControl.Library;
import Audino.MediaControl.Playlist;

import java.io.FileNotFoundException;
import java.io.IOException;

import Audino.MediaControl.AudioControl;

import Audino.State.PlayerState.PausedState;
import Audino.State.PlayerState.PlayingState;
import Audino.State.PlayerState.ReadyState;
import Audino.State.PlayerState.PlayerState;
import Audino.State.PlayerState.UnreadyState;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import javafx.util.Duration;

/*
 * This class takes care of playing audio and all of the basic functions
 * such as pause/play and stop.
 */
public class Player {
    
	// =============================================================== ( instance )
	/*
     * The instance variables are limited to what is useful, for example AudioStream
     * is not an instance variable because we don't need a specific AudioStream to
     * go between functions.
     */
    private Track currentTrack;
    private PlayerState state;
    private double volume = 1;
    private Playlist playlist;
    private Library Library;
    private AudioControl audioController;
    private MediaPlayer mediaPlayer;
    private boolean paused;
    private boolean playing;

    // =============================================================== ( getters )
    
    /**
     * Gets the current state of the player.
     * @return PlayerState The state of the player
     */
    public PlayerState getState() {
        return this.state;
    }
    
    
    /**
     * Gets the current Library of the player.
     * @return Library The library of the player
     */
    public Library getLibrary() {
        return this.Library;
    }
    
    /**
     * Gets the current playlist of the player.
     * @return Playlist The playlist of the player.
     */
    public Playlist getPlaylist() {
        return this.playlist;
    }
    /**
     * Gets the current mediaplayer's status
     * @return MediaPlayer.Status the current status of the mediaplayer.
     */
    public Status getStatus(){
        if (this.mediaPlayer == null){
            return null;
        }
        return this.mediaPlayer.getStatus();
    }
    
    // =============================================================== ( setters )

    /**
     * Passes a new playlist into the player.
     * @param playlist The playlist to be passed into the player.
     */
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        loadTrackFromPlaylist();
    }

    
    /**
     * Passes a new track into the player.
     * @param track The track to be passed into the player.
     */
    public void setTrack(Track track) {
        this.playlist = new Playlist(track);
        loadTrackFromPlaylist();
    }
    /**
     * Tells the player to load a track from the playlist
     *
     */
    public void loadTrackFromPlaylist(){
        try{
            stopPlayback();
            this.currentTrack = playlist.getCurrentTrack();
            if(this.currentTrack == null){
                this.state = new UnreadyState(this);
            }
            else {
                this.mediaPlayer = new MediaPlayer(this.currentTrack.getMedia());
                this.state = new ReadyState(this);
            }
        }
        catch (IOException e){
            this.state = new UnreadyState(this);
        }
    }
    // =============================================================== ( constructors )
    
    /*
     * Sets every instance variable to null or 0
     */
    public Player() {
        this.state = new UnreadyState(this);
        this.playlist = new Playlist();
        this.mediaPlayer = null;
        this.currentTrack = null;
        
        try {
            this.Library = Library.deserialize();
            if (this.Library == null){
                    this.Library = new Library();
            }
        } catch (ClassNotFoundException | IOException e) {
            this.Library = new Library();
        }
    }


    // =============================================================== ( methods )
    // These two below don't work at the moment
    // /**
    //  * Tests whether or not a clip is playing.
    //  * @return Boolean true if clip is playing, false otherwise
    //  */
    // public boolean isPlaying() {
    //     return this.playing;
    // }
    // /**
    //  * Tests whether or not a clip is paused.
    //  * @return Boolean true if a clip is paused, false otherwise.
    //  */
    // public boolean isPaused() {
    //     return this.paused;
    // }
    /*
     * Plays the clip (starts audio)
     */
    public void startPlayback() {
        MediaPlayer player = this.mediaPlayer;
        if (this.currentTrack == null){
            this.state = new UnreadyState(this);
            return; // Things are critically screwed up here
        }
        if (player != null) {
            Status status = mediaPlayer.getStatus();
            switch (status) {
            case PAUSED:
            case STOPPED:
            case READY:
                player.play();
                this.state = new PlayingState(this);
                break;
            default:
                newPlayer();
                break;
            }
        } else {
            newPlayer();
        }
    }
    /*
     * Stops the clip.
     */
    public void stopPlayback() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.setOnStopped(() -> {
                    this.state = new ReadyState(this);
                    this.paused = false;
                    this.playing = false;
            });
        }
        else {
            this.state = new UnreadyState(this);
        }
    }
   
    /*
     * Stops the clip and remembers where it left off, in order to be able to resume
     * from the same point again.
     */
    public void pausePlayback() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.pause();
            this.mediaPlayer.setOnPaused(() -> {
                this.state = new PausedState(this);
                this.paused = true;
                this.playing = false;
            });

        }
        else {
            this.state = new UnreadyState(this);
        }
    }
    
    /**
     * Moves the currentTime of the active mediaPlayer forward by 1000ms.
     */
    public void fastForward() {
        if (mediaPlayer != null) {
            Duration toAdd = new Duration(1000);
            Duration newTime = mediaPlayer.getCurrentTime().add(toAdd);
            mediaPlayer.seek(newTime);
            mediaPlayer.play();
            this.state = new PlayingState(this);
        }
    }
    
    /**
     * Moves the currentTime of the active mediaPlayer backward by 1000ms.
     */
    public void rewind() {
        if (mediaPlayer != null) {
            Duration toAdd = new Duration(-1000);
            Duration newTime = mediaPlayer.getCurrentTime().add(toAdd);
            mediaPlayer.seek(newTime);
            mediaPlayer.play();
            this.state = new PlayingState(this);
        }
    }
    
    /**
     * Seeks the currentTime of the active mediaPlayer to a specified location.
     * @param seekTo DOUBLE The time to seek to.
     */
    public void seek(double seekTo) {
        if (mediaPlayer != null){
            Duration time = new Duration(seekTo * 1000);

            mediaPlayer.seek(time);
        }
    }
    
    /**
     * Plays the next song in the loaded playlist.
     */
    public void playNext() {
        this.playlist.getState().onNext();
        this.currentTrack = playlist.getCurrentTrack();
        startPlayback();
    }
    
    /**
     * Plays the previous song in the loaded playlist
     */
    public void playPrevious() {
        this.playlist.getState().onPrevious();
        this.currentTrack = playlist.getCurrentTrack();
        startPlayback();
    }

    /**
     * Destroys the old player, if present and creates a new one with the a new
     * track from the playlist
     */
    private void newPlayer() {
        try {
            Media file = currentTrack.getMedia();
            this.mediaPlayer.dispose();
            this.mediaPlayer = new MediaPlayer(file);
            this.mediaPlayer.setVolume(volume);
            this.mediaPlayer.setOnReady(() -> {
                this.mediaPlayer.play();
            });
            this.mediaPlayer.setOnPlaying(() -> {
                this.playing = true;
                this.paused = false;
                this.state = new PlayingState(this);

            });
        } catch (IOException e) {
            this.state = new UnreadyState(this);
        }
    }
    
    
}
