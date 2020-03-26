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
	
    private String title;
    private String album;
    private String artist;
    private String track;
    private String year;
    private double duration = 0;
    private File file;
    private static final long serialVersionUID = 1L;
    
    // =============================================================== ( getters )
    
    /**
     * Returns the title of the track.
     *
     * @return String the track's title.
     */
    public String getTitle() {
        return this.title;
    }
	
    /**
     * Returns the album of the track.
     *
     * @return String the track's album.
     */
    public String getAlbum() {
        return this.album;
    }
  
    /**
     * Returns the artist of the track.
     *
     * @return String the track's artist.
     */
    public String getArtist() {
        return this.artist;
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
     * Returns the playtime as a string, which is the minutes concatenated with seconds
     *
     * @return minutes + ":" + seconds of track
     */
    public String getPlaytime() {
        // casting to int just removes the .0 at the end of seconds and minutes
        String minutes = ((int)Math.floor(this.duration / 60)) + "";
        String seconds = ((int)this.duration % 60) + "";
        return minutes + ":" + seconds;
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

    /**
     * Returns a media version of the file for use in the gui
     *
     * @return file as a Media filetype
     */
    public Media getMedia() throws IOException {
        String cleanString = file.toURI().toString();
        Media toReturn = new Media(cleanString);
        return toReturn;
    }
    // =============================================================== ( Property Getters )
    /**
     * Returns simple title property for use in GUI
     *
     * @return title of track as SimpleStringProperty
     */
    public SimpleStringProperty titleProperty(){
        SimpleStringProperty toReturn = new SimpleStringProperty(this.title);
        return toReturn;
    }
    /**
     * Returns simple album property for use in GUI
     *
     * @return album of track as SimpleStringProperty
     */
    public SimpleStringProperty albumProperty(){
        SimpleStringProperty toReturn = new SimpleStringProperty(this.album);
        return toReturn;
    }
    /**
     * Returns simple artist property for use in GUI
     *
     * @return artist of track as SimpleStringProperty
     */
    public SimpleStringProperty artistProperty(){
        SimpleStringProperty toReturn = new SimpleStringProperty(this.artist);
        return toReturn;
    }
    /**
     * Returns simple track property for use in GUI
     *
     * @return track of track as SimpleStringProperty
     */
    public SimpleStringProperty trackProperty(){
        SimpleStringProperty toReturn = new SimpleStringProperty(this.track);
        return toReturn;
    }
    /**
     * Returns simple track property for use in GUI
     *
     * @return track of track as SimpleStringProperty
     */
    public SimpleStringProperty yearProperty() {
        SimpleStringProperty toReturn = new SimpleStringProperty(this.year);
        return toReturn;
    }
    /**
     * Returns simple playtime property for use in GUI
     *
     * @return playtime of track as SimpleStringProperty
     */
    public SimpleStringProperty playtimePropertY(){
        SimpleStringProperty toReturn = new SimpleStringProperty(getPlaytime());
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
            this.artist = metadata.get(0);
            this.album = metadata.get(1);
            this.title = metadata.get(2);
            this.track = metadata.get(3);
            this.year = metadata.get(4);
            double metadataDuration = Double.parseDouble(metadata.get(5));
            this.duration = metadataDuration;

        } else {
            this.artist = "????";
            this.album = "????";
            this.title = "????";
            this.track = "????";
            this.year = "????";
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
