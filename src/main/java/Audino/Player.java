import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

import java.util.Scanner;
// /Users/ryanmchale/Desktop/crazylungs.wav

/* Hey guys, I've got the audio player up and running, run it and type in
*  the Directory Path to a .wav file of your choosing on your computer. 
*  I'm starting to half understand the sun.audio package and I managed to 
*  get a stop button working, going to try and figure out a pause button now.
*  But yeah this seems to work pretty well, try it out and let me know if
*  you find any bugs!
*  -Ryan
*/
public class Player {
	
	 /*	I got some help from the internet on this part, It essentially reads
	 * 	a file directory and then stores that as a file and converts it to
	 *	an input stream, which by the name I can sort of understand what that
	 *  is, but I'd be lying if I said I fully understood what it is. 
	 *	It then converts that InputStream into an AudioStream and then plays
	 *	that Audiostream. I added a return to this so that we can use that
	 *	AudioStream outside of this method, which allows us to pause later.
	 */
	  public static Clip PlaySound(String filename) throws Exception{
		  {
		    //InputStream in = new AudioInputStream(filename);
			  
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());

			Clip clip = AudioSystem.getClip();
			
			clip.open(stream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			return clip;
		    
		  }
	  }
	 
	  
	  public static void main(String[] args) throws Exception {
		  	 //I set these up for the pause function.
			 boolean run = true;
			 String status;
			 
			 /*
			  * Setting up a scanner to take the next input for a file directory, 
			  * ie) /Users/ryanmchale/Desktop/crazylungs.wav
			  * I haven't found a way to error test whether or not they enter a 
			  * correct directory path yet.
			  */
			 
			 Scanner file = new Scanner(System.in);
			 Scanner running = new Scanner(System.in);
			 System.out.println("Enter Directory Path to a .wav file: ");
			 String filename = file.next();

			 
			 /* This calls the PlaySound method from above, which starts the audio playing
			  * and also stores the current AudioStream in the variable 'sound', so that
			  * we can interact with said AudioStream, I needed it to stop the stream.
			  */
			 
			 Clip sound = PlaySound(filename);
			 /*
			  * This block of code allows the user to run commands on the AudioStream,
			  * and makes sure that any invalid command is fixed.
			  * This will come in handy when we have more functions than stop.
			  */
			 while(run == true) {
				 System.out.println("To stop playback, type \"stop\" ");
				 status = running.next();
				 //Making it non-case sensitive.
				 status = status.toLowerCase();
				 if(status.contentEquals("stop")) run = false;
				 //If they type the wrong thing, they can keep trying til they type 'stop'.
				 else {
					 System.out.println("You typed: " +status);
					 System.out.println("Invalid Entry. ");
				 }
			 }
			 
			 /* Once 'stop' is typed, this block will run, stopping the AudioStream and
			  * ending the program.
			  */
			 
			 sound.stop();
			 System.out.println("Goodbye.");
			 }
}


