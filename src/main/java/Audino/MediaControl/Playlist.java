package Audino.MediaControl;

import java.util.ArrayList;

import Audino.State.PlaylistState.DefaultState;
import Audino.State.PlaylistState.PlaylistState;

public class Playlist {
    
    // ====================================================================== ( instance )

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

    // ====================================================================== ( constructors )

    /**
     * Creates a null playlist in the default state.
     */
    public Playlist() {
        this.state = new DefaultState(this);
    }

    /**
     * Creates a playlist initialized with a track in the default state.
     * @param aTrack a Track to be added to the playlist.
     */
    public Playlist(Track track) {
        this.tracks.add(track);
        this.state = new DefaultState(this);
    }
    
    /**
     * Creates a playlist initialized with an ArrayList of tracks in the default state.
     * @param tracks an ArrayList<Track> filled with the tracks to be added.
     */
    public Playlist(ArrayList<Track> tracks) {
        for (Track t: tracks) {
            this.tracks.add(t);
        }
        System.out.println(this.tracks.size());
        this.state = new DefaultState(this);
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

    // ====================================================================== ( methods )
	
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
}
