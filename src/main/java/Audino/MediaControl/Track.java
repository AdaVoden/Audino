package Audino.MediaControl;

import Audino.Utility.MetadataParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

/**
 * Representation of audio file data, including metadata for ease of access, duration for playback purposes
 * and the file itself to be loaded into a player. Defaults to ???? if the audio file is playable but has
 * no metadata, as to prevent null errors.
 *
 * Takes metadata information using the jaudiotagger import
 *
 *
 */
public class Track implements Serializable {
	
	// =============================================================== ( instance )
	
    private SimpleStringProperty title;
    private SimpleStringProperty album;
    private SimpleStringProperty artist;
    private SimpleStringProperty track;
    private SimpleStringProperty year;
    private double duration = 0;
    private File file;
    private static final long serialVersionUID = 2L;
    
    // =============================================================== ( getters )
    
    /**
     * Returns the title of the track.
     *
     * @return String the track's title.
     */
	public String getTitle() {
		return title.get();
	}
	
    /**
     * Returns the album of the track.
     *
     * @return String the track's album.
     */
	public String getAlbum() {
		return album.get();
	}
  
	/**
     * Returns the artist of the track.
     *
     * @return String the track's artist.
     */
	public String getArtist() {
		return artist.get();
	}
  
	/**
     * Returns the duration of the track.
     *
     * @return double the track's duration.
     */
    public double getDuration() {
		return duration;
	}

    /**
     * Returns new file reference
     *
     * @return New file reference
     * @throws IOException
     */
	public File getFile() {
      File newReference = null;
      try {
          newReference = new File(this.file.getCanonicalPath());
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          //This shouldn't fire because we've already done this work
      }
      return newReference;
	}
    //TODO return true if the file's hash is the same along with location
	
	public Media getMedia() throws IOException {
      String cleanString = file.toURI().toString();
      Media toReturn = new Media(cleanString);
      return toReturn;
	}
    
    // =============================================================== ( constructors )
    
    /**
     * Only constructor for Track as the file location is the only thing
     * that is needed to produce a track, everything else is for
     * user friendliness.
     */
    public Track(String fileLocation) {
    	
        this.file = new File(fileLocation);
        ArrayList<String> metadata = MetadataParser.parseAudio(this.file);
        
        //TODO replace with enum?
        
        if (metadata != null){
            this.artist = new SimpleStringProperty(metadata.get(0));
            this.album = new SimpleStringProperty(metadata.get(1));
            this.title = new SimpleStringProperty(metadata.get(2));
            this.track = new SimpleStringProperty(metadata.get(3));
            this.year = new SimpleStringProperty(metadata.get(4));
            double metadataDuration = Double.parseDouble(metadata.get(5));
            this.duration = metadataDuration;

        } else {
            this.artist = new SimpleStringProperty("????");
            this.album = new SimpleStringProperty("????");
            this.title = new SimpleStringProperty("????");
            this.track = new SimpleStringProperty("????");
            this.year = new SimpleStringProperty("????");
            this.duration = 0;
        }
    }
    
    // =============================================================== ( methods ) 

    /**
     * Compares two tracks and returns true if equal. It's only the same if the data is the same.
     *
     *
     * @param other A potentially different track to compare with
     * @return Boolean true if they are the same track, false otherwise
     */
    public Boolean equals(Track other){
      if (!other.album.equals(this.album)){
          return false;
      }
      if (!other.artist.equals(this.artist)) {
          return false;
      }
      if (!other.title.equals(this.title)) {
          return false;
      }
      if (other.duration != this.duration) {
          return false;
      }
      return true;
    }
}
