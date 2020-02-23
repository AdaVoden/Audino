import java.io.*;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Line;
import java.util.Scanner;

public class AudioFXTest {
	public static Mixer mixer;
	public static Clip clip;
	public static File testSound = new File("test.wav");
	
	public static float gainLvl = 0;
	public static float panLvl = 0;
	public static boolean muteValue = false;

	/*
	* This is just some temporary code that plays a short audio file,
	* once the player is more fleshed out i'll swap this code out for it afterwards.
	* -----
	* this was also written just to try and get a general understanding of java's sound api
	*/
	public static void play(File sound) {
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try {
			clip = (Clip)mixer.getLine(dataInfo);
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}	

		// opens the clip with selected audio file: 
		try {
			clip.open(AudioSystem.getAudioInputStream(testSound));
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// adjusts gain
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gainLvl);

		// adjusts panning (not sure how useful this'll be in the context of listening to music, but it's here lol)
		FloatControl panControl = (FloatControl)clip.getControl(FloatControl.Type.PAN);
		panControl.setValue(panLvl);

		// mute toggle, (again, not sure how useful, especially considering that clips pre-stored into memory)
		BooleanControl muteToggle = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
		muteToggle.setValue(muteValue);

		// starts playing the clip: 
		clip.start();

		// to prevent java from terminating immediately,
		// we sleep for the amount of milliseconds the clip lasts for
		try {
			Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public static void gainAdjust(float gain) {
		gainLvl = gain;
	}

	public static void panAdjust(float pan) {
		panLvl = pan;
	}

	public static void muteToggle(boolean mute) {
		muteValue = mute;
	}

	public static void main(String[] args) {
		Scanner gainSc = new Scanner(System.in);

		// obtains a mixer (in this case the default audio device of my mac): 
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[2]);

		// plays sound untouched
		System.out.println("Currently playing default...");
		play(testSound);

		System.out.print("Enter gain: ");
		String g = gainSc.nextLine();
		float gain = Float.parseFloat(g);
		gainAdjust(gain);

		// plays sound after gain is adjusted
		System.out.println("Currently playing gain adjustment...");
		play(testSound);
		
		System.out.print("Enter pan: ");
		String p = gainSc.nextLine();
		float pan = Float.parseFloat(p);
		panAdjust(pan);

		// plays sound after panning is adjusted
		System.out.println("Currently playing pan adjustment...");
		play(testSound);
	}
}