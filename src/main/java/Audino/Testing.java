package Audino;

import java.util.Scanner;

public class Testing {
	public static void main(String[] args) throws Exception
	{	  
		boolean running = true;
		boolean loaded = false;
		boolean pause = false;
		boolean exitQ;
		Scanner sc1 = new Scanner(System.in);
		String status;
		String path;
		AudioFile AudioFile;
		
		System.out.println("DIRECTORY PATH TO .WAV FILE: ");
		path = sc1.next();
		AudioFile = new AudioFile(path);
		AudioFile.playClip();
		loaded = true;
		exitQ = true;
		
		 while(running == true)
		 {
			if(loaded == false)
			{
				System.out.println("DIRECTORY PATH TO .WAV FILE: ");
				path = sc1.next();
				AudioFile = new AudioFile(path);
				AudioFile.playClip();
				loaded = true;
				exitQ = true;
				
			}

			 if(pause == false)	System.out.println("To pause playback, type \"pause\".\r\nTo stop playback, type \"stop\". ");
			 if(pause == true)	System.out.println("To resume playback, type \"play\".\r\nTo stop playback, type \"stop\". ");

			 status = sc1.next().toLowerCase();
			 if(pause == true)
			 {
				 if(status.contentEquals("pause")) System.out.println("Already paused...");
			 }
			 
			 if(pause == false)
			 {
				 if(status.contentEquals("pause"))
				 {
					 pause = true;
					 AudioFile.pauseClip();
				 }
			 }

			 
			 if(status.contentEquals("stop"))
			 {
				 AudioFile.stopClip();
				 System.out.println("Exit Program? \n\r\"Y\"For Yes, \"N\" For No. ");
				 while(exitQ == true)
				 {
				 status = sc1.next().toLowerCase();
				 if(status.contentEquals("y")) 
					 {
					 running = false ; 
					 exitQ = false;
					 }
				 if(status.contentEquals("n"))
					 {
					 exitQ = false;
					 loaded = false;
					 }
				 if(exitQ == true) System.out.println("Invalid Input. ");
				 }

			 }
			 if(pause == true)
			 {
				 if(status.contentEquals("play"))
				 {
					AudioFile.playClip();
					 pause = false;
				 }
			 }
			 else if(status.contentEquals("play")) System.out.println("Already playing... ");
			 if(!status.contentEquals("pause") && !status.contentEquals("stop") && !status.contentEquals("play") && !status.contentEquals("y") && !status.contentEquals("n"))
			 {
				 System.out.println("Invalid Input... ");
			 }
			 
		 } 
		 sc1.close();
		 System.out.println("Goodbye... ");
	}
}

