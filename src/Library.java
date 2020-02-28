package Audino;

import Audino.Track;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.lang.ClassNotFoundException;

import java.util.ArrayList;

/**
 * Disk-writeable and readable permanent storage for all tracks that are
 * consumed from a user-defined folder list. Saved to disk to prevent loss and
 * re-iteration through potentially vast amounts of track data. Only one file
 * name is allowed to prevent duplication.
 *
 * Written by: Harlan Shaw
 * Email: harlan.shaw@ucalgary.ca
 */
public class Library implements Serializable {

    // ====================================================================== ( instance )
    private final ArrayList<Track> trackList = new ArrayList<Track>();
    private ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
    private static final long serialVersionUID = 4L;
    private static final String fileName = "Library.ser";

    // ====================================================================== ( getters )

    /**
     * Getter for playlistList
     * @return ArrayList<Playlist> Returns playlists stored in the library.
     */
    public ArrayList<Playlist> getPlaylists() {
        ArrayList<Playlist> cloneList = new ArrayList<Playlist>();
        for(Playlist p : playlistList) {
            cloneList.add(new Playlist(p));
        }
        return cloneList;
    }

    /**
     * Getter for trackList. 
     *
     * @return ArrayList<Track> Returns the tracks stored in the library.
     */
    public ArrayList<Track> getTrackList(){
        ArrayList<Track> cloneList = new ArrayList<Track>();
        for(Track t : trackList) {
            cloneList.add(t);
        }
        return cloneList;
    }

    // ====================================================================== ( setters )

    /**
     * Setter for adding playlists.
     * @param aPlaylistName The name of the playlist to be added.
     */
    public void addPlaylist(String aPlaylistName) {
        playlistList.add(new Playlist(aPlaylistName));
        playlistCreatedSuccess(aPlaylistName);
    }

    /**
     * Setter for removing playlists.
     * @param aPlaylistName The name of the playlist to be deleted.
     */
    public void removePlaylist(String aPlaylistName) {
        boolean match = false;
        int index = 0;
        for (Playlist p : playlistList) {
            if (p.getName().equals(aPlaylistName)) {
                index = playlistList.indexOf(p);
                match = true;
            }
        }
        if (match == true) {
            playlistList.remove(index);
            playlistDeletedSuccess(aPlaylistName);
        }
        else if (match == false) {
            playlistDNE_Error(aPlaylistName);
        }
    }

    /**
     * Setter for adding tracks.
     * @param aTrackDirectory Directory of track to be added.
     */
    public void addTrack(String aTrackDirectory) {
        Track newTrack = new Track(aTrackDirectory);
        trackList.add(newTrack);
        trackAddedSuccess(newTrack.getTitle());
    }

    /**
     * Setter for removing tracks.
     * @param aTrackName Name of track to be removed.
     */
    public void removeTrack(String aTrackName) {
        boolean match = false;
        int index = 0;
        for (Track t : trackList) {
            if (t.getTitle().equals(aTrackName)) {
                index = trackList.indexOf(t);
                match = true;
            }
        }
        if (match == true) {
            trackList.remove(index);

        }
    }

    /**
     * Setter for adding tracks to a playlist.
     * @param aTrack The track to be added.
     * @param aPlaylist The name of the playlist to be added to.
     */
    public void addTrackToPlaylist(Track aTrack, String aPlaylist) {
        for (Playlist p : playlistList) {
            if (p.getName().equals(aPlaylist)) {
                p.addTrack(new Track(aTrack));
                trackAddedToPlaylistSuccess(aTrack.getTitle(), aPlaylist);
            }
        }
    }

    /**
     * Setter for removing tracks from a playlist.
     * @param aTrack The track to be removed.
     * @param aPlaylist The name of the playlist to be removed from.
     */
    public void removeTrackFromPlaylist(Track aTrack, String aPlaylist) {
        for (Playlist p : playlistList) {
            if (p.getName().equals(aPlaylist)) {
                p.removeTrack(aTrack);
                trackRemovedFromPlaylistSuccess(aTrack.getTitle(), aPlaylist);
            }
        }
    }

    // ====================================================================== ( toString )

    /**
     * toString for all playlists in library.
     * @return String containing all playlist names.
     */
    public String playlistsToString() {
        String allPlaylists = "";
        for (Playlist p : playlistList) {
            allPlaylists += p.getName() + ", ";
        }
        return allPlaylists.substring(0, allPlaylists.length() - 2);
    }

    /**
     * toString for all tracks in library.
     * @return String containing all track names.
     */
    public String tracksToString() {
        String allTracks = "";
        for (Track t : trackList) {
            allTracks += t.getTitle() + ", ";
        }
        return allTracks.substring(0, allTracks.length() - 2);
    }
    // ====================================================================== ( methods )

    /**
     * Writes any given library file to disk with library name for re-use.
     *
     * @param obj Library that's going to be written to disk.
     */
    public void serialize(final java.io.Serializable obj) throws IOException{
        try {
            final ObjectOutputStream libOut = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
            libOut.writeObject(obj);
            libOut.close();
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
     public static Library deserialize() throws IOException, ClassNotFoundException{
        try {
            ObjectInputStream lib = new ObjectInputStream(new FileInputStream(new File(fileName)));

            Library toReturn = (Library) lib.readObject();
            lib.close();
            return toReturn;
        }
        catch (final IOException e){
            // TODO
            e.printStackTrace();
        }
        catch (final ClassNotFoundException e){
            // TODO
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Finds tracks from user-defined folder list for import into library.
     *
     * @param folderList List of folders that
     */
    public void collectTracks(final ArrayList<String> folderList){
        for (final String file: folderList) {
            final Track newTrack = new Track(file);
            trackList.add(newTrack);
            // TODO Skip/Retry/Ignore bad trackfile?
        }
    }
    
    // ============================================== existence booleans

    /**
     * Checks if a playlist by given name exists in library.
     * @param aPlaylist The name of the playlist in question.
     * @return True if playlist exists, else false.
     */
    public boolean playlistExists(String aPlaylist) {
        for(Playlist p : playlistList) {
            if (p.getName().equals(aPlaylist)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a track by the given name exists in library.
     * @param aTrack The name of the track in question.
     * @return True is song exists, else false.
     */
    public boolean trackExists(String aTrack) {
        for(Track t : trackList) {
            if (t.getTitle().equals(aTrack)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a track by given name exists in playlist.
     * @param aSong The name of the song in question.
     * @param aPlaylist The name of the playlist in question.
     * @return  True if song exists in playlist, else false.
     */
    public boolean trackExistsInPlaylist(String aTrack, String aPlaylist) {
        for(Playlist p : playlistList) {
            if (p.getName().equals(aPlaylist)) {
                for(Track t : p.getTracks()) {
                    if (t.getTitle().equals(aTrack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // ====================================================================== ( output messages )
    
    public void invalidNameError(String aPlaylist) {
        
        System.out.println("ERROR : The name \"" + aPlaylist + "\" is not valid.");
        System.out.println();
        System.out.print("Enter another command : ");
        
    }

    public void quitMessage() {        

        System.out.println("TERMINATED.");

    }

    // ============================================== error

    public void playlistExistsError(String aPlaylist) {
        
        System.out.println("ERROR : A playlist by the name \"" + aPlaylist + "\" already exists.");
        System.out.println();
        System.out.print("Enter another command : ");
        
    }

    public void playlistDNE_Error(String aPlaylist) {
        
        System.out.println("ERROR : A playlist by the name \"" + aPlaylist + "\" does not exist.");
        System.out.println();
    
    }

    public void trackExistsInPlaylistError(String aTrack, String aPlaylist) {
        
        System.out.println("ERROR : \"" + aTrack + "\" already exists in \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void trackDNE_InPlaylistError(String aTrack, String aPlaylist) {
        
        System.out.println("ERROR : \"" + aTrack + "\" does not exist in \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void syntaxError() {
        System.out.println("ERROR : Syntax not recognized.");
        System.out.println();
        System.out.print("Enter another command : ");

    }

    public void noPlaylistsError() {
        System.out.println("ERROR : No playlists found.");
        System.out.println();
        System.out.print("Enter another command : ");

    }

    // ============================================== success

    public void playlistCreatedSuccess(String aPlaylist) {
        
        System.out.println("SUCCESS : Playlist called \"" + aPlaylist + "\" created.");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void playlistDeletedSuccess(String aPlaylist) {
        
        System.out.println("SUCCESS : Playlist called \"" + aPlaylist + "\" deleted.");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void trackAddedSuccess(String aTrack) {

        System.out.println("SUCCESS : \"" + aTrack + "\" has been added.");
        System.out.println();
        System.out.print("Enter another command : ");

    }

    public void trackRemovedSuccess(String aTrack) {

        System.out.println("SUCCESS : \"" + aTrack + "\" has been removed.");
        System.out.println();
        System.out.print("Enter another command : ");

    }

    public void trackAddedToPlaylistSuccess(String aTrack, String aPlaylist) {
        
        System.out.println("SUCCESS : \"" + aTrack + "\" has been added to \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void trackRemovedFromPlaylistSuccess(String aTrack, String aPlaylist) {
        
        System.out.println("SUCCESS : \"" + aTrack + "\" has been removed from \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void playlistsToStringSuccess() {
        
        System.out.println("SUCCESS : all playlists listed.");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void tracksToStringSuccess() {

        System.out.println("SUCCESS : all tracks listed.");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    // ============================================== command menus

    public void printCommands() {
        System.out.println("====================- ( Commands ) -=======================");
        System.out.println("    1. Play / Pause"                                        );
        System.out.println("    2. Play next track (TODO)"                              );
        System.out.println("    3. Play previous track (TODO)"                          );
        System.out.println("    4. Stop loaded track (TODO)"                            );
        System.out.println("    5. Load song from file"                                 );
        System.out.println("    6. Create a Playlist"                                   );
        System.out.println("    7. Edit Playlist"                                       );
        System.out.println("    8. Load playlist (TODO)"                                );
        System.out.println("    9. Library(TODO)"                                       );
        System.out.println("    10. Status"                                             );
        System.out.println("    0. Quit program"                                        );
        System.out.println("===========================================================");
        System.out.println();
        System.out.print("Enter a command # : ");
    }

    public void printPlaylistEditor(String aPlaylistName) {
        System.out.println("NOW EDITING : " + aPlaylistName);
        System.out.println("===========================");
        System.out.println("    1. Add track"           );
        System.out.println("    2. Remove track"        );
        System.out.println("    3. Edit playlist name"  );
        System.out.println("    4. Exit editor"         ); // TODO: add delete option
        System.out.println("===========================");
        System.out.println();
        System.out.print("Enter a command # : ");
    }

    public void printPlaylistCommands() {
        System.out.println("==============================");
        System.out.println("    1. Play / Pause"           );
        System.out.println("    2. Load next track"        );
        System.out.println("    3. Load previous track"    );
        System.out.println("    4. Unload current playlist");
        System.out.println("=============================="); // TODO: update so that next / prev track can be stored in
        System.out.println();                                 // status.
        System.out.print("Enter a command # : ");

    }

    // public void status() {
    //     System.out.println("The current loaded song is : ");
    //     System.out.println("The current playlist loaded is : ");
    //     System.out.println("The next track in the playlist is : ");
    //     System.out.println("The previous track in the playlist is : ");
    // }
}

