/*
/Users/ryanmchale/desktop/crazylungs16.wav
 */

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.Scanner;
public class Audio 
{
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
	  
  
 
	 
	  public static void main(String[] args) throws Exception
	  {
			  
			 boolean run = true;
			 boolean pause = false;
			 String status;
				 
			 long setTime = 0; 
			  
			 Scanner file = new Scanner(System.in);
			 Scanner running = new Scanner(System.in);
			 
			 System.out.println("Enter Directory Path to a .wav file: ");
			 String fileName = file.next();
			 
			 Clip currentClip = playSound(fileName);
			 
			 while(run == true)
			 {
				 if(pause == false)	System.out.println("To pause playback, type \"pause\".\r\nTo stop playback, type \"stop\". ");
				 if(pause == true)	System.out.println("To resume playback, type \"play\".\r\nTo stop playback, type \"stop\". ");

				 status = running.next();
				 if(pause == true)
				 {
					 if(status.contentEquals("pause")) System.out.println("Already paused...");
				 }
				 
				 if(pause == false)
				 {
					 if(status.contentEquals("pause"))
					 {
						 pause = true;
						 setTime = currentClip.getMicrosecondPosition();
						 currentClip.stop();
						 
						 while(currentClip.getMicrosecondLength() < setTime )
						 {
							 setTime = setTime - currentClip.getMicrosecondLength();
						 }
						 
						 
					 }
				 }
	
				 
				 if(status.contentEquals("stop"))
				 {
					 System.out.println("Goodbye... ");
					 currentClip.stop();
					 run = false;

				 }
				 if(pause == true)
				 {
					 if(status.contentEquals("play"))
					 {
						currentClip = playSoundAtTime(fileName, setTime);
						 pause = false;
					 }
				 }
				 else if(status.contentEquals("play")) System.out.println("Already playing... ");
				 if(!status.contentEquals("pause") && !status.contentEquals("stop") && !status.contentEquals("play"))
				 {
					 System.out.println("Invalid Input... ");
				 }

			 } 
			 file.close();
			 running.close();
			 currentClip.close();
	   }
	   }

	 	  

