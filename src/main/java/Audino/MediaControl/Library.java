package Audino.MediaControl;

import Audino.MediaControl.Track;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.lang.ClassNotFoundException;

import java.util.ArrayList;

/**
 * Disk-writeable and readable permanent storage for all tracks that are
 * consumed from a user-defined folder list. Saved to disk to prevent loss and
 * re-iteration through potentially vast amounts of track data. Only one file
 * name is allowed to prevent duplication.
 *
 *
 *
 */


public class Library implements Serializable {
	
    private ArrayList<Track> trackList = new ArrayList<Track>();
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private ArrayList<File> folderList = new ArrayList<File>();
    private int playlistIndex = 0;
    private static final long serialVersionUID = 4L;
    private static final String fileName = "Library.ser";

    /**
     * Writes any given library file to disk with library name for re-use.
     *
     * @param obj Library that's going to be written to disk.
     */
    public void serialize() throws IOException{
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            final ObjectOutputStream libOut = new ObjectOutputStream(file);
            libOut.writeObject(this);
            libOut.close();
            file.close();
        }
        catch(final IOException e){
            // TODO
            e.printStackTrace();
        }
    }
   
    /**
     * Reloads the library data into memory.
     *
     * @return Library the library.ser file that was written to disk
     */
    public static Library deserialize() throws ClassNotFoundException, IOException {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream lib = new ObjectInputStream(file);

            Library toReturn = (Library) lib.readObject();
            lib.close();
            file.close();
            return toReturn;

    }
    
    /**
     * Finds tracks from user-defined folder list for import into library.
     *
     * @param folderList List of folders that
     */
    public void collectTracks(final ArrayList<String> folderList) {
        for (String file: folderList) {
            Track newTrack = new Track(file);
            if (newTrack != null){
                trackList.add(newTrack);
            }
            // TODO Skip/Retry/Ignore bad trackfile?
        }
    }
    
    /**
     * Getter for trackList. Currently is a privacy leak.
     *
     * @param
     * @return ArrayList<Track> Returns the tracks stored in the library
     */
    public ArrayList<Track> getTrackList(){
        return trackList;
    }
    
    /**
     * Getter for playlists.
     * @return ArrayList<Playlist> Returns the playlists stored in the library.
     */
    public ArrayList<Playlist> getPlaylists() {
    	return playlists;
    }
    
    /**
     * Adds a playlist to the library.
     * @param toAdd : The playlist to be added.
     */
    public void addPlaylist(Playlist toAdd) {
    	playlists.add(new Playlist(toAdd));
    }

    /**
     * Gets the index of the current playlist from the library.
     * @return int The current index.
     */
    public int getPlaylistIndex() {
    	return this.playlistIndex;
    }
    
    /**
     * Setter for the playlist index of the library.
     * @param toSet : The integer to be set.
     */
    public void setPlaylistIndex(int toSet) {
    	if (0 <= toSet && toSet < playlists.size()) {
    		this.playlistIndex = toSet;
    	}
    }
}
