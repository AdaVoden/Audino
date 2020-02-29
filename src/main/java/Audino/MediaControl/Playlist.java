package Audino.MediaControl;
/*
to add:
    - ability to create playlist names with spaces
    - Player can read playlists and play them
    - User has the ability to add multiple songs to a playlist on one command
    - toStrings
*/


import java.util.ArrayList;

public class Playlist {
    
    // ====================================================================== ( instance )

    private String name;
    private ArrayList<Track> tracks = new ArrayList<Track>();
    
    // ====================================================================== ( getters )

    public String getName() {
        return this.name;
    }
    
    public ArrayList<Track> getTracks() {
        ArrayList<Track> cloneList = new ArrayList<Track>();
        for(Track t : tracks) {
            cloneList.add(t);
        }
        return cloneList;
    }

    // ====================================================================== ( setters )

    public void setName(String aName) {
        if (aName.indexOf(' ') != -1) {
            System.out.println("ERROR: Names cannot contain spaces.");
        }
        else {
            name = aName;
        }      
    }
    
    public void addTrack(Track aTrack) {
        tracks.add(aTrack);
    }
    
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

    public String tracksToString(String playlistName) {
        String allTracks = "";
        for (Track t : tracks) {
            allTracks += t.toString() + ", ";
        }
        return allTracks.substring(0, allTracks.length() - 2);
    }

    // ====================================================================== ( methods )

    public static Boolean validName(String aName) {
        if (aName.equals("") == true || aName.indexOf(' ') != -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
