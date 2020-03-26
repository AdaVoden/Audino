package Audino.MediaControl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Audino.State.PlaylistState.DefaultState;
import Audino.State.PlaylistState.PlaylistState;
import javafx.beans.property.SimpleStringProperty;


public class Playlist implements Serializable {

    // ====================================================================== ( instance )

    final static private long serialVersionUID = 2L;
    private SimpleStringProperty name = new SimpleStringProperty("Default");
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private PlaylistState state;
    private int trackIndex = 0;
    private boolean shuffle = false;

    // ====================================================================== ( getters )

    /**
     * Gets all the tracks in a playlist.
     * @return ArrayList<Track> ArrayList containing all songs in the playlist.
     */
    public ArrayList<Track> getTracks() {
        ArrayList<Track> cloneList = new ArrayList<Track>();
        for(Track s : tracks) {
            cloneList.add(s);
        }
        return cloneList;
    }
    /**
     * Gets the size of a playlist.
     * @return int The size of the playlist.
     */
    public int getPlaylistSize() {
        return this.tracks.size();
    }
    /**
     * Gets the current trackIndex of a playlist.
     * @return int The trackIndex of the playlist.
     */
    public int getTrackIndex() {
        return this.trackIndex;
    }

    /**
     * Gets the current Track of a playlist.
     * @return Track The current Track of the playlist.
     */
    public Track getCurrentTrack() {
        if (tracks.size() >= 1){
            return this.tracks.get(trackIndex);
        }
        return null;
    }

    /**
     * Gets the current state of a playlist.
     * @return PlaylistState The current state of the playlist.
     */
    public PlaylistState getState() {
        return this.state;

    }
    /**
     * Gets the shuffle state of a playlist.
     * @return The current shuffle state of the playlist.
     */
    public Boolean getShuffle() {
      return this.shuffle;
    }
    /**
     * Returns the property of a playlist's name
     * @return Name property of playlist
     */
    public SimpleStringProperty nameProperty(){
        return this.name;
    }
    /**
     * Gets the name of a playlist.
     * @return The name of the playlist.
     */
    public String getName() {
      return name.get();
    }

    // ====================================================================== ( setters )

    /**
     * Sets the current state of shuffle instance variable
     * @param toSet boolean value for shuffle.
     */
    public void setShuffle(boolean toSet) {
        this.shuffle = toSet;
    }

    /**
     * Sets the trackIndex instace variable to a specified value.
     * @param newIndex int of the index to be set.
     */
    public void setIndex(int newIndex) {
        this.trackIndex = newIndex;
    }

    /**
     * Sets the state of a playlist to specified state.
     * @param state PlaylistState of what state is to be set.
     */
    public void setState(PlaylistState state) {
        this.state = state;
    }
    /**
     * Adds a track to a playlist.
     * @param aTrack The track to be added.
     */
    public void addTrack(Track aTrack) {
        tracks.add(aTrack);
    }
    /**
     * Adds an ArrayList of tracks to a playlist.
     * @param tracks ArrayList<Track> containing the tracks to be added.
     */
    public void addTrack(ArrayList<Track> tracks){
        tracks.addAll(tracks);
    }

    /**
     * Removes a track from a playlist.
     * @param aTrack The track to be removed.
     */
    public void removeTrack(Track aTrack) {
        tracks.remove(aTrack);
    }
    /**
     * Takes a string for a name and converts it to SimpleStringProperty
     * before storing it in the playlist.
     * @param aName String containing desired name
     */
    public void setName(String aName) {
      name = new SimpleStringProperty(aName);
    }

    // ====================================================================== ( constructors )

    /**
     * Creates a null playlist in the default state.
     */
    public Playlist() {
        this.state = new DefaultState(this);
    }

    /**
     * Creates a playlist initialized with a track in the default state.
     * @param track a Track to be added to the playlist.
     */
    public Playlist(Track track) {
        this.tracks.add(track);
        this.state = new DefaultState(this);
    }

    /**
     * Creates a playlist initialized with a file, in the default state
     * @throws IOException if the file cannot be loaded the playlist is broken and cannot continue
     * @param track a file to be turned into a track and added to the playlist
     */
    public Playlist(File track) throws IOException {
        Track t = new Track(track.getCanonicalPath());
        this.tracks.add(t);
        this.state = new DefaultState(this);
    }
    /**
     * Creates a playlist initialized with an ArrayList of tracks in the default state.
     * @throws IOException if the file cannot be loaded the playlist is broken and cannot continue
     * @param tracks an ArrayList<Track> filled with the tracks to be added.
     */
    public Playlist(List<File> tracks) throws IOException {
        for (File f : tracks) {
            try {
                Track t = new Track(f.getCanonicalPath());
                this.tracks.add(t);
            }
            catch (IOException e) {
                //skip for now
            }
        }
        if (this.tracks.size() == 0){
            throw new IOException("no files loaded");
        }
        this.state = new DefaultState(this);

    }
    /**
     * Creates a playlist as a copy of an existing playlist.
     * @param toCopy Playlist to be copied.
     */
    public Playlist(Playlist toCopy) {
      this.tracks.addAll(toCopy.getTracks());
      state = toCopy.getState();
      trackIndex = toCopy.getTrackIndex();
      shuffle = toCopy.getShuffle();
    }
    /**
     * Creates a playlist with a name.
     * @param name The name of the playlist.
     */
    public Playlist(String name) {
      this.name = new SimpleStringProperty(name);
      this.state = new DefaultState(this);
    }
    /**
     * Creates a playlist with default name, from files opened from disk
     * @param tracks All the tracks in a List<File>
     */
    public Playlist(List<File> tracks) throws IOException {
        for (File f : tracks) {
            try {
                Track t = new Track(f.getCanonicalPath());
                this.tracks.add(t);
            } catch (IOException e) {
                // skip for now
            }
        }
        if (this.tracks.size() == 0) {
            throw new IOException("no files loaded");
        }
        this.name = new SimpleStringProperty("Default");
        this.state = new DefaultState(this);

    }


    }
    // ====================================================================== ( toString )

    /**
     * Lists all the songs in a playlist in a string.
     * @return String containing all song names.
     */
    public String songsToString() {
        String allSongs = "";
        for (Track s : tracks) {
            allSongs += s.getFile().getName() + ", ";
        }
        return allSongs.substring(0, allSongs.length() - 2);
    }
}
