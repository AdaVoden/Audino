package Audino;
import org.apache.tika.metadata.Metadata;
import org.apache.commons.io.FilenameUtils;

public class Track {
    private String title;
    private String album;
    private String artist;
    private double duration = 0.0;
    private String fileLocation;

    public Track(String fileLocation) {
        Metadata metadata = MetadataParser.parseAudio(fileLocation);
        this.title = metadata.get("title");
        if (FilenameUtils.getExtension(fileLocation).equals("wav")){
            this.album = metadata.get("album");
            System.out.print(metadata.toString());
        }
        else {
            this.album = metadata.get("xmpDM:album");
        }
        this.artist = metadata.get("xmpDM:artist");
        // this.duration = Double.parseDouble(metadata.get("xmpDM:duration"));
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

}
