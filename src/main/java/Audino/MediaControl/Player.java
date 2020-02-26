package Audino.MediaControl;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
    private File file;
    private Clip currentClip;
    private long startTime;
    private boolean isPlaying;

    //Getters
    /*
     * getFile returns a copy of File 'file' of the Player that this getter is
     * used on.
     */
    public File getFile()
    {
        String path = this.file.getAbsolutePath();
        return new File(path);
    }
    /*
     * getCurrentClip returns the clip that is currently loaded, and not a copy,
     * because if it were a copy then it would have no use, because we cannot
     * perform any functions such as pause or stop on the clip that is playing
     * unless we have access to that specific clip.
     */
    public Clip getCurrentClip()
    {
        return this.currentClip;
    }
    /*
     * getStartTime returns a long which is the number of microseconds of the clip
     * that has already played
     */
    public long getStartTime()
    {
        return this.startTime;
    }

    /*
     * getIsPlaing returns a boolean that tells you if there is a clip playing or
     * not.
     */
    public boolean getIsPlaying()
    {
        return this.isPlaying;
    }
    //Setters

    /*
     * setFile sets the instance variable 'file' to whatever file is at the
     * directory path inputed.
     */
    public void setFile(String filePath)
    {
        this.file = new File(filePath);
    }

    /*
     * setStartTime sets the instance variable 'startTime' to whatever long is
     * inputed. This value is read in micro seconds, and is used to resume playing
     * at the same place that the clip was paused.
     */
    public void setStartTime(long microSec)
    {
        this.startTime = microSec;
    }
    //Constructors
    /*
     * Sets every instance variable to null or 0
     */
    public Player()
    {
        this.file = null;
        this.startTime = 0;
    }

    /*
     * Sets 'file' to a new File from the inputed directory path
     */
    public Player(String fileDir) throws Exception
    {
        this.file = new File(fileDir);
    }

    /*
     * Sets 'file' to a new File from the inputed directory path and sets
     * 'startTime' to the inputed long (in microseconds).
     */
    public Player(String fileDir, long microSec) throws Exception
    {
        this.file = new File(fileDir);
        this.startTime = microSec;
    }
    //Methods
    /*
     * Plays the clip (starts audio)
     */
    public void playClip() throws Exception
    {
        AudioInputStream stream = AudioSystem.getAudioInputStream(this.getFile().getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(stream);
        this.isPlaying = true;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.setMicrosecondPosition(startTime);
        clip.start();
        this.currentClip = clip;
        stream.close();
    }

    /*
     * Stops the clip.
     */
    public void stopClip()
    {
        this.isPlaying = false;
        this.getCurrentClip().stop();
        this.getCurrentClip().close();
    }
    /*
     * Stops the clip and remembers where it left off, in order to be able to resume
     * from the same point again.
     */
    public void pauseClip()
    {
        this.isPlaying = false;
        long time = this.getCurrentClip().getMicrosecondPosition();
        while(time>this.getCurrentClip().getMicrosecondLength())
            {
                time = time - this.getCurrentClip().getMicrosecondLength();
            }
        this.setStartTime(time);
        this.currentClip.stop();
        this.currentClip.close();
    }

}
