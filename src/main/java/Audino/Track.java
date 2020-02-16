package Audino;


import org.apache.tika.metadata.Metadata;



public class Track {
    private String title;
    private String album;
    private String artist;
    private double duration;
    private String fileLocation;

    public Track(String fileLocation) {
        Metadata metadata = MetadataParser.parseAudio(fileLocation);
        this.title = metadata.get("title");
        this.album = metadata.get("album");
        this.artist = metadata.get("xmpDM:artist");
        this.duration = Double.parseDouble(metadata.get("xmpDM:duration"));
    }
}
