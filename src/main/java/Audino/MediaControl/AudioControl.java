package Audino.MediaControl;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.BooleanControl;

/**
*
*
*/
public class AudioControl {
    private float gainLvl;
	private float panLvl;
    private boolean muteState;
    private Player player;
    
    // constructors
    public AudioControl(Player player) {
        this.player = player;
        this.gainLvl = 0;
        this.panLvl = 0;
        this.muteState = false;
    }

    public AudioControl(Player player, float gain, float pan, boolean mute) {
        this.player = player;
        this.gainLvl = gain;
        this.panLvl = pan;
        this.muteState = mute;
    }

    // getters
    public float getCurrentGain() {
        return this.gainLvl;
    }

    public float getCurrentPan() {
        return this.panLvl;
    }

    public boolean getMuteState() {
        return this.muteState;
    }
    
    // setters
    public void setGain(float gain) {
        gainLvl = gain;
        Clip clip = player.getCurrentClip();
        FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gainLvl);
    }

    public void setPan(float pan) {
        panLvl = pan;
        Clip clip = player.getCurrentClip();
        FloatControl panControl = (FloatControl)clip.getControl(FloatControl.Type.PAN);
		panControl.setValue(panLvl);
    }

    public void toggleMute(boolean mute) {
        muteState = mute;
        Clip clip = player.getCurrentClip();
        BooleanControl muteToggle = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
		muteToggle.setValue(muteState);
    }

    // to string
    public String toString() {
        return "gain level: " + gainLvl +
               "\n pan level: " + panLvl +
               "\n mute state: " + muteState;
    }

    public static void main(String[] args) {

    }
}