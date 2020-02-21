import java.io.*;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
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

	/*
	* This is just some temporary code that plays a short audio file,
	* once the player is more fleshed out i'll swap this code out for it afterwards
	*/
	
	public static void play(File sound, Float gain) {
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
		
		// set gain level: 
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gain);

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

	/*
	public static void gainAdjust(double gain) {
		// set gain level: 
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float)gain);
	}
	*/

	public static void main(String[] args) {
		Scanner gainSc = new Scanner(System.in);

		// obtains a mixer (in this case the default audio device of my mac): 
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[2]);

		play(testSound, 0f);

		System.out.print("Enter gain: ");
		String g = gainSc.nextLine();
		float gain = Float.parseFloat(g);
		//gainAdjust(gain);
		
		play(testSound, gain);
	}
}