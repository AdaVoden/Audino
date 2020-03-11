package Audino.MediaControl;

import Audino.MediaControl.Library;
import Audino.MediaControl.Playlist;

import java.io.FileNotFoundException;
import java.io.IOException;

import Audino.MediaControl.AudioFX;

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
    private AudioFX audioFX;
    private MediaPlayer mediaPlayer;

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
    
    // =============================================================== ( setters )

    /**
     * Passes a new playlist into the player.
     * @param playlist The playlist to be passed into the player.
     */
    public void setPlaylist(Playlist playlist) {
        stopPlayback();
        this.playlist = playlist;
        this.currentTrack = this.playlist.getCurrentTrack();
        this.state = new ReadyState(this);
    }
    
    /**
     * Passes a new track into the player.
     * @param track The track to be passed into the player.
     */
    public void setTrack(Track track) {
        try {
            stopPlayback();
            this.currentTrack = track;
            this.mediaPlayer = new MediaPlayer(track.getMedia());
            this.playlist = new Playlist(track);
            this.state = new ReadyState(this);
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
        } catch (ClassNotFoundException e) {
            this.Library = new Library();
        }
    }


    // =============================================================== ( methods )
    
    /**
     * Tests whether or not a clip is playing.
     * @return Boolean true if clip is playing, false otherwise
     */
    public boolean isPlaying() {
        if (mediaPlayer != null) {
            Status status = mediaPlayer.getStatus();
            if (status.equals(MediaPlayer.Status.PLAYING)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tests whether or not a clip is paused.
     * @return Boolean true if a clip is paused, false otherwise.
     */
    public boolean isPaused() {
        if (mediaPlayer != null){
            Status status = mediaPlayer.getStatus();
            if (status.equals(MediaPlayer.Status.PAUSED)) {
                return true;
            }
        }

        return false;
  
    }
    
    /*
     * Plays the clip (starts audio)
     */
    public void startPlayback() {
        if (this.mediaPlayer != null) {
        	
            Status status = mediaPlayer.getStatus();
            Media file;
            
            switch (status) {
            case PAUSED:
                mediaPlayer.play();
                this.state = new PlayingState(this);
                break;
                
            case UNKNOWN:
                try {
                    file = currentTrack.getMedia();
                    MediaPlayer player = new MediaPlayer(file);
                    player.setVolume(volume);
                    this.state = new PlayingState(this);
                    player.setOnReady(() -> {
                         player.play();
                        });
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    this.state = new UnreadyState(this);
                }
                break;
                
            case PLAYING:
                // Do nothing
                break;
                
            case STOPPED:
                this.mediaPlayer.play();
                this.state = new PlayingState(this);
                break;

            case READY:
                this.mediaPlayer.play();
                this.state = new PlayingState(this);

                break;
            case DISPOSED:
                try {
                    file = currentTrack.getMedia();
                    MediaPlayer player = new MediaPlayer(file);
                    player.setVolume(volume);
                    this.state = new PlayingState(this);

                    player.setOnReady(() -> {
                        player.play();
                    });
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    this.state = new UnreadyState(this);
                }
                break;
                
            }
        }

        else {
            try {
                Media file = currentTrack.getMedia();
                this.mediaPlayer = new MediaPlayer(file);
                
                this.mediaPlayer.setOnReady(() ->{
                        this.mediaPlayer.setVolume(volume);
                        this.mediaPlayer.play();
                        this.state = new PlayingState(this);
                    });
            }
            catch (IOException e) {
                this.state = new UnreadyState(this);
            }
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
            Duration time = new Duration(seekTo);
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
    
    
    
}
