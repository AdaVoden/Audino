package Audino.MediaControl;

import Audino.Utility.MetadataParser;
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
 * Takes metadata information using the tika metadata import.
 *
 * Written by: Harlan Shaw
 * Email: harlan.shaw@ucalgary.ca
 */
public class Track implements Serializable {
    private String title;
    private String album;
    private String artist;
    private String track;
    private String year;
    private int duration = 0;
    private File file;
    private static final long serialVersionUID = 2L;
    /**
     * Only constructor for Track as the file location is the only thing
     * that is needed to produce a track, everything else is for
     * user friendliness.
     */
    public Track(String fileLocation) {
        this.file = new File(fileLocation);
        ArrayList<String> metadata = MetadataParser.parseAudio(this.file);
        //TODO replace with enum?
        this.artist = metadata.get(0);
        this.album = metadata.get(1);
        this.title = metadata.get(2);
        this.track = metadata.get(3);
        this.year = metadata.get(4);
        int metadataDuration = Integer.parseInt(metadata.get(5));
        this.duration = metadataDuration;
    }
  /**
   * Returns the title of the track.
   *
   * @return String the track's title.
   */
	public String getTitle() {
		return title;
	}
  /**
   * Returns the album of the track.
   *
   * @return String the track's album.
   */
	public String getAlbum() {
		return album;
	}
  /**
   * Returns the artist of the track.
   *
   * @return String the track's artist.
   */
	public String getArtist() {
		return artist;
	}
  /**
   * Returns the duration of the track.
   *
   * @return double the track's duration.
   */
    public int getDuration() {
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
		Media toReturn = new Media(this.file.getCanonicalPath());
		return toReturn;
	}
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
