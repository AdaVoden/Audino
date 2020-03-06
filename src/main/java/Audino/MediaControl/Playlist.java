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
import Audino.State.PlaylistState.State;

public class Playlist {
    
    // ====================================================================== ( instance )

    private ArrayList<Track> tracks = new ArrayList<Track>();
    private State state;
    private int trackIndex = 0;
    private boolean shuffle = false;

    // ====================================================================== ( getters )

    public ArrayList<Track> getSongs() {
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

    // ====================================================================== ( setters )

    public void setShuffle(boolean toSet) {
    	this.shuffle = toSet;
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
	
	public Track nextTrack() {
		if (this.trackIndex < tracks.size() - 1) {
			trackIndex++;
			return tracks.get(trackIndex);
		}
		return tracks.get(trackIndex);
	}
	public Track previousTrack() {
		if (this.trackIndex > 0) {
			trackIndex--;
			return tracks.get(trackIndex);
		}
		return tracks.get(trackIndex);
	}
    public void addTrack(Track aTrack) {
        tracks.add(aTrack);
    }

    public void removeTrack(Track aTrack) {
        tracks.remove(aTrack);
     }
    public Track getCurrentTrack() {
    	return this.tracks.get(trackIndex);
    }

}
    
    
    
    
    
    
    
    
    
    
