package Audino.MediaControl;

import java.util.ArrayList;

public class Playlist {
    
    // ====================================================================== ( instance )

    private String name;
    private ArrayList<Track> tracks = new ArrayList<Track>();
    
    // ====================================================================== ( getters )

    /**
     * Getter for Playlist name.
     * @return String containing playlist name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for all tracks in a Playlist.
     * @return ArrayList<Track> containing all tracks stored in playlist.
     */
    public ArrayList<Track> getTracks() {
        ArrayList<Track> cloneList = new ArrayList<Track>();
        for(Track t : tracks) {
            cloneList.add(t);
        }
        return cloneList;
    }

    // ====================================================================== ( setters )

    /**
     * Setter for playlist name.
     * @param aName The desired name of playlist.
     */
    public void setName(String aName) {
        if (aName.indexOf(' ') != -1) {
            System.out.println("ERROR: Names cannot contain spaces.");
        }
        else {
            name = aName;
        }      
    }
    
    /**
     * Setter for adding tracks to a playlist.
     * @param aTrack The track to be added.
     */
    public void addTrack(Track aTrack) {
        tracks.add(aTrack);
    }
    
    /**
     * Setter for removing tracks from a playlist.
     * @param aTrack The track to be removed.
     */
    public void removeTrack(Track aTrack) {
        tracks.remove(aTrack);
     }

    // ====================================================================== ( constructors )
    // ============================================== from new
    
    public Playlist(String aName) {
        if (aName.equals("") == false && aName.indexOf(' ') == -1) {
            name = aName;
        }
        else {
            System.out.println("ERROR: Invalid name.");
        }
    }

    // ============================================== copy

    public Playlist(Playlist aPlaylist) {
        name = aPlaylist.getName();
        tracks = aPlaylist.getTracks();
    }

    // ====================================================================== ( toString )

    /**
     * toString for all Tracks in a Playlist.
     * @param playlistName The name of the playlist who's tracks will be listed.
     * @return
     */
    public String tracksToString(String playlistName) {
        String allTracks = "";
        for (Track t : tracks) {
            allTracks += t.toString() + ", ";
        }
        return allTracks.substring(0, allTracks.length() - 2);
    }

    // ====================================================================== ( methods )

    /**
     * Checks if a given name is valid.
     * @param aName The name to be checked.
     * @return Boolean: true if name is valid, else false.
     */
    public static Boolean validName(String aName) {
        if (aName.equals("") == true || aName.indexOf(' ') != -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
