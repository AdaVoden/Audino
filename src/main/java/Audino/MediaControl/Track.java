package Audino.MediaControl;

import Audino.Utility.MetadataParser;

import org.apache.tika.metadata.Metadata;

import java.io.Serializable;

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
    private double duration = 0.0;
    private String fileLocation;
    private static final long serialVersionUID = 2L;
    /**
     * Only constructor for Track as the file location is the only thing
     * that is needed to produce a track, everything else is for
     * user friendliness.
     */
    public Track(String fileLocation) {
        Metadata metadata = MetadataParser.parseAudio(fileLocation);
        this.title = metadata.get("title");
        //TODO Wav
        if (FilenameUtils.getExtension(fileLocation).equals("wav")){
            System.out.println(metadata.toString());
        }
        // else {
            this.album = metadata.get("xmpDM:album");
        // }

        this.artist = metadata.get("xmpDM:artist");
        // this.duration = Double.parseDouble(metadata.get("xmpDM:duration"));
        if (this.title == null){
            this.title = "????";
        }
        if (this.album == null){
            this.album = "????";
        }
        if (this.artist == null){
            this.artist = "????";
        }
    }

    public Track(Track toCopy) {
        title = toCopy.title;
        album = toCopy.album;
        artist = toCopy.artist;
        duration = toCopy.duration;
        fileLocation = toCopy.fileLocation;
        // serialVersionUID = toCopy.serialVersionUID;  <---- not sure how to fix this
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
    public double getDuration() {
		return duration;
	}
  /**
   * Returns the location of the track on disk.
   *
   * @return String the track's location on disk.
   */
	public String getFileLocation() {
		return fileLocation;
	}
  /**
   * Compares two tracks and returns true if equal. It's only the same if the data is the same.
   * TODO: Return true if the file's hash is the same along with location
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
