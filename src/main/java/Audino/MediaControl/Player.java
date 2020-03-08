package Audino.MediaControl;

import Audino.MediaControl.Library;
import Audino.MediaControl.Playlist;

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

    //Getters
    /*
     * getFile returns a copy of File 'file' of the Player that this getter is
     * used on.
     */
    // public File getFile()
    // {
    //     String path = this.file.getAbsolutePath();
    //     return new File(path);
    // }
    /*

      /*
      * IsPlaying returns a boolean that tells you if there is a clip playing or
      * not.
      */
    public boolean IsPlaying()
    {
        if (mediaPlayer != null) {
            Status status = mediaPlayer.getStatus();
            if (status.equals(MediaPlayer.Status.PLAYING)) {
                return true;
            }
        }

        return false;
    }
    public boolean IsPaused() {
        if (mediaPlayer != null){
            Status status = mediaPlayer.getStatus();
            if (status.equals(MediaPlayer.Status.PAUSED)) {
                return true;
            }
        }

        return false;
  
    }
    //Setters

    /*
     * setFile sets the instance variable 'file' to whatever file is at the
     * directory path inputed.
     */

    //Constructors
    /*
     * Sets every instance variable to null or 0
     */
    public Player()
    {
        this.state = new UnreadyState(this);
        this.playlist = new Playlist();
        this.mediaPlayer = null;
        this.currentTrack = null;
        
        try {
            this.Library = Library.deserialize();
        } catch (ClassNotFoundException | IOException e) {
            this.Library = new Library();
        }
    }

    /*
     * Sets 'file' to a new File from the inputed directory path
     */
    // public Player(String fileDir) throws Exception
    // {
    //     this.file = new File(fileDir);
    // }

    /*
     * Sets 'file' to a new File from the inputed directory path and sets
     * 'startTime' to the inputed long (in microseconds).
     */
    // public Player(String fileDir, long microSec) throws Exception
    // {
    //     this.file = new File(fileDir);
    //     this.startTime = microSec;
    // }
    //Methods
    /*
     * Plays the clip (starts audio)
     */
    public void startPlayback()
    {
        if (this.mediaPlayer != null){
            Status status = mediaPlayer.getStatus();
            Media file;
            switch (status){
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
                        mediaPlayer.setVolume(volume);
                        mediaPlayer.play();
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
    public void stopPlayback()
    {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.setOnStopped(() -> {
                    mediaPlayer.dispose();
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
    public void pausePlayback()
    {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.setOnPaused(() -> {
                this.state = new PausedState(this);
            });

        }
        else {
            this.state = new UnreadyState(this);
        }
    }
    public void fastForward() {
        if (mediaPlayer != null) {
            Duration toAdd = new Duration(1000);
            Duration newTime = mediaPlayer.getCurrentTime().add(toAdd);
            mediaPlayer.seek(newTime);
            mediaPlayer.play();
            this.state = new PlayingState(this);
        }
    }
    public void rewind() {
        if (mediaPlayer != null) {
            Duration toAdd = new Duration(-1000);
            Duration newTime = mediaPlayer.getCurrentTime().add(toAdd);
            mediaPlayer.seek(newTime);
            mediaPlayer.play();
            this.state = new PlayingState(this);
        }
    }
    public void seek(double seekTo){
        if (mediaPlayer != null){
            Duration time = new Duration(seekTo);
            mediaPlayer.seek(time);
        }
    }
    public void playNext() {
        playlist.getState().onNext();
        this.currentTrack = playlist.getCurrentTrack();
        startPlayback();
    }
    public void playPrevious() {
        playlist.getState().onPrevious();
        this.currentTrack = playlist.getCurrentTrack();
        startPlayback();
    }
    public PlayerState getState() {
        return this.state;
    }
    public Library getLibrary() {
        return this.Library;
    }
    public Playlist getPlaylist() {
        return this.playlist;
    }
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        this.currentTrack = this.playlist.getCurrentTrack();
        this.state = new ReadyState(this);
    }
    public void setTrack(Track track) {
        this.currentTrack = track;
        this.playlist = new Playlist(track);
        this.state = new ReadyState(this);
    }
}
