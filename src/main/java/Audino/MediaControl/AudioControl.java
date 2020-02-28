package Audino.MediaControl;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.BooleanControl;

/*
*
*/
public class AudioControl {
    private float gainLvl;
	private float panLvl;
    private boolean muteState = false;
    
    
    public void setGain(float gain, Clip clip) {
        gainLvl = gain;
        FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gainLvl);
    }

    public void setPan(float pan, Clip clip) {
        panLvl = pan;
        FloatControl panControl = (FloatControl)clip.getControl(FloatControl.Type.PAN);
		panControl.setValue(panLvl);
    }

    public void toggleMute(boolean mute, Clip clip) {
        muteState = mute;
        BooleanControl muteToggle = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
		muteToggle.setValue(muteState);
    }

    public String toString() {
        return "gain level: " + gainLvl +
               "\n pan level: " + panLvl +
               "\n mute state: " + muteState;
    }

    public static void main(String[] args) {

    }
}