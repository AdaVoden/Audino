package Audino;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class AudioFile {
	
private File file;
private Clip currentClip;
private long startTime;

//Getters
public File getFile()
{
	String path = this.file.getAbsolutePath();
	return new File(path);
}
public Clip getCurrentClip()
{
	return this.currentClip;
}
public long getStartTime()
{
	return this.startTime;
}
//Setters
public void setFile(String filePath)
{
	this.file = new File(filePath);
}
public void setStartTime(long microSec)
{
	this.startTime = microSec;
}
//Constructors
public AudioFile()
{
	this.file = null;
	this.startTime = 0;
}
public AudioFile(String fileDir) throws Exception
{
	this.file = new File(fileDir);
}
public AudioFile(String fileDir, long microSec) throws Exception
{
	this.file = new File(fileDir);
	this.startTime = microSec;
}
//Methods

public void playClip() throws Exception 
{
	AudioInputStream stream = AudioSystem.getAudioInputStream(this.getFile().getAbsoluteFile());
	Clip clip = AudioSystem.getClip();
	clip.open(stream);
	clip.loop(Clip.LOOP_CONTINUOUSLY);
	clip.setMicrosecondPosition(startTime);
	clip.start();	
	this.currentClip = clip;
	stream.close();
}

public void stopClip()
{
	this.getCurrentClip().stop();
	this.getCurrentClip().close();
}
public void pauseClip()
{
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
