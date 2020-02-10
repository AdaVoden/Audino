
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import java.util.Scanner;
public class Audio {
	
	  public static Clip playSound(String filename) throws Exception
	  {
		  {			  
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());

			Clip clip = AudioSystem.getClip();
			
			clip.open(stream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();	
			return clip;

				
			}
		  }	
	  
	  public static Clip playSoundAtTime(String filename, long startTime) throws Exception
	  {
	  {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());

			Clip clip = AudioSystem.getClip();
			
			clip.open(stream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.setMicrosecondPosition(startTime);
			clip.start();	
			return clip;

	  }
	  }
	  

	  
	  public static Clip pause(Clip clip,long startTime, String filename) throws Exception
	  {
		  clip.stop();
		  Scanner scan = new Scanner(System.in);
		  String status;
		  boolean pause = true;
		  while(pause = true)
		  {
			  System.out.println("To resume playback, type \"play\". ");
			  status = scan.next();
			  if(status.contains("play"))
			  {
				  pause = false;
				  return playSoundAtTime(filename,startTime);
			  }
			  else
			  {
				  System.out.println("Invalid input. ");
			  }
		  }
		  
		  return clip;
		  
	  }
	  
	  
	 
	  public static void main(String[] args) throws Exception
	  {
			  
			 boolean run = true;
			 boolean pause;
			 String status;
				 
			 long setTime; 
			  
			 Scanner file = new Scanner(System.in);
			 Scanner running = new Scanner(System.in);
			 
			 System.out.println("Enter Directory Path to a .wav file: ");
			 String fileName = file.next();
			 
			 Clip currentClip = playSound(fileName);
			 
			 while(run == true)
			 {
				 System.out.println("To pause playback, type \"pause\"\r\nTo stop playback, type \"stop\". ");
				 status = running.next();
				 
				 if(status.contentEquals("pause"))
				 {
					 setTime = currentClip.getMicrosecondPosition();
					 //
					 while(currentClip.getMicrosecondLength() < setTime )
					 {
						 setTime = setTime - currentClip.getMicrosecondLength();
					 }
					 currentClip = pause(currentClip,setTime,fileName);
					 
				 }
				 else if(status.contentEquals("stop"))
				 {
					 System.out.println("Goodbye... ");
					 currentClip.stop();
					 run = false;

				 }
				 else
				 {
					 System.out.println("Invalid Input. ");
				 }

			 } 
	   }
}