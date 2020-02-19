package Audino;
import org.apache.tika.metadata.Metadata;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

public class Track implements Serializable {
    private String title;
    private String album;
    private String artist;
    private double duration = 0.0;
    private String fileLocation;

    public Track(String fileLocation) {
        Metadata metadata = MetadataParser.parseAudio(fileLocation);
        this.title = metadata.get("title");
        //TODO Wav
        if (FilenameUtils.getExtension(fileLocation).equals("wav")){
            this.album = metadata.get("album");
        }
        else {
            this.album = metadata.get("xmpDM:album");
        }
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

	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtist() {
		return artist;
	}

	public double getDuration() {
		return duration;
	}

	public String getFileLocation() {
		return fileLocation;
	}
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
