package Audino.MediaControl;
/*
  to add:
  - ability to create playlist names with spaces
  - Player can read playlists and play them
  - User has the ability to add multiple songs to a playlist on one command
  - toStrings
*/


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

    public ArrayList<Track> getTracks() {
        ArrayList<Track> cloneList = new ArrayList<Track>();
        for(Track s : tracks) {
            cloneList.add(s);
        }
        return cloneList;
    }
    
    public int getPlaylistSize() {
        return this.tracks.size();
    }
    
    public int getTrackIndex() {
        return this.trackIndex;
    }

    public Track getCurrentTrack() {
        return this.tracks.get(trackIndex);
    }

    public PlaylistState getState() {
        return this.state;

    }

    // ====================================================================== ( setters )

    public void setShuffle(boolean toSet) {
        this.shuffle = toSet;
    }

    public void setIndex(int newIndex) {
        this.trackIndex = newIndex;
    }

    public void setState(PlaylistState state) {
        this.state = state;
    }

    // ====================================================================== ( constructors )

    public Playlist() {
        this.state = new DefaultState(this);
    }

    public Playlist(Track aTrack) {
        tracks.add(aTrack);
        this.state = new DefaultState(this);
    }
    
    public Playlist(ArrayList<Track> tracks) {
        this.tracks.addAll(tracks);
        this.state = new DefaultState(this);
    }

    // ====================================================================== ( toString )

    public String songsToString() {
        String allSongs = "";
        for (Track s : tracks) {
            allSongs += s.getFile().getName() + ", ";
        }
        return allSongs.substring(0, allSongs.length() - 2);
    }

    // ====================================================================== ( methods )
	
    public void addTrack(Track aTrack) {
        tracks.add(aTrack);
    }
    public void addTrack(ArrayList<Track> tracks){
        tracks.addAll(tracks);
    }

    public void removeTrack(Track aTrack) {
        tracks.remove(aTrack);
    }
}
