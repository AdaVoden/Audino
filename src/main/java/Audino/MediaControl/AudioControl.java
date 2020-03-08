package Audino.MediaControl;

import Audino.MediaControl.Player;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.BooleanControl;

/**
 * Basic controls for Player such as volume (gain), panning and mute. 
 */
public class AudioControl {
    private Player player;
    private float gainLvl;
	private float panLvl;
    private boolean muteState;
    
    /**
     * Constructor for AudioControl. Takes a player, and sets all other instance variables to their defaults.
     * 
     * @param player The player in which AudioControl manipulates.
     */
    public AudioControl(Player player) {
        this.player = player;
        this.setGain(0);
        this.setPan(0);
        this.setMute(false);
    }

    /**
     * Constructor for AudioControl.
     * 
     * @param player The player in which AudioControl manipulates.
     * @param gain The gain value.
     * @param pan The pan value.
     * @param mute The mute state.
     */
    public AudioControl(Player player, float gain, float pan, boolean mute) {
        this.player = player;
        this.setGain(gain);
        this.setPan(pan);
        this.setMute(mute);
    }

    public float getCurrentGain() {
        return this.gainLvl;
    }

    public float getCurrentPan() {
        return this.panLvl;
    }

    public boolean getMuteState() {
        return this.muteState;
    }
    
    /**
     * Sets gain value for Player.
     * 
     * @param gain Gain value, max value 6.0206 (inclusive). If value outside range is given, defaults to 0.
     */
    public void setGain(float gain) {
        Clip clip = player.getCurrentClip();

        if (gain <= 6.0206) {
            gainLvl = gain;
            
        } else {
            gainLvl = 0;
        }

        FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(gainLvl);
    }

    /**
     * Sets panning value for Player.
     *
     * @param pan Panning value, ranges from -1 to 1 (inclusive). If value outside range is given, defaults to 0.
     */
    public void setPan(float pan) {
        Clip clip = player.getCurrentClip();
        
        if (pan <= 1 && pan >= -1) {
            panLvl = pan;
        } else {
            panLvl = 0;
        }

        FloatControl panControl = (FloatControl)clip.getControl(FloatControl.Type.PAN);
		panControl.setValue(panLvl);
    }

    /**
     * Sets mute state for Player.
     *
     * @param mute Mute state, either true or false.
     */
    public void setMute(boolean mute) {
        Clip clip = player.getCurrentClip();
        muteState = mute;
        BooleanControl muteToggle = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
		muteToggle.setValue(muteState);
    }

    /** 
     * To string method for AudioControl.
     * 
     * @return The gain level, pan level, and mute state for Player.
     */
    public String toString() {
        return "gain level: " + gainLvl +
               "\n pan level: " + panLvl +
               "\n mute state: " + muteState;
    }
}