package Audino;

import java.io.File;
// import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import Audino.MediaControl.AudioFile;
import Audino.MediaControl.Playlist;
import Audino.MediaControl.Library;
import Audino.MediaControl.Track;

import org.apache.commons.io.FilenameUtils;

/**
 * Runner class. This is the main code that initializes and runs the project.
 *
 * Written by: Harlan Shaw
 * Email: harlan.shaw@ucalgary.ca
 */
public class App {
    /**
     * Main entry point into application, runs basic text UI and calls other functions as necessary
     *
     *@param args The list of commandline arguments pass into the application
     */
    public static void main(String[] args){

        ArrayList<String> status = new ArrayList<String>();
        status.add("The current loaded song is : ");
        status.add("The current playlist loaded is : ");
        status.add("The next track in the playlist is : ");
        status.add("The previous track in the playlist is : ");


        //Initialize variables.
        Library library = new Library();
        AudioFile audioFile = new AudioFile();
        Scanner scanner = new Scanner(System.in);

        try {
            //If any file is added to the args we want to try and play it, but only the first one
            for (String arg: args){
                File input = new File(arg);
                if (input.isFile()){
                    audioFile = new AudioFile(input.toString());
                    audioFile.playClip();
                    break;
                }
            }

            while (true) {

                // input loop, run indefinitely until quit
                clearScreen();
                library.printCommands();
                String line = scanner.nextLine();

                switch (line) {
                case "1": //play/pause

                    if (audioFile != null){

                        if (audioFile.getIsPlaying()) {
                            audioFile.pauseClip();
                        }
                        else {
                            audioFile.playClip();
                        }
                    }

                    else {
                        System.out.println("No track has been loaded.");
                    }

                    break;

                case "2": //next track

                    break;

                case "3": //prev track

                    break;

                case "4": //stop track

                    audioFile.stopClip();

                    break;

                case "5": //load track

                    System.out.print("Please enter filepath of track : ");
                    line = scanner.nextLine();
                    library.addTrack(line);

                    audioFile = new AudioFile(line);

                    //This may be better as a hashmap
                    status.set(0, "The current loaded song is: " + FilenameUtils.getName(line));
                    clearScreen();

                    break;

                case "6": //create playlist

                    System.out.print("Enter the name of the playlist to be created : ");
                    String playlistName = scanner.nextLine();

                    Boolean playlistExists = library.playlistExists(playlistName);

                    if (playlistExists == false) {
                        library.addPlaylist(playlistName);
                    }

                    else if (playlistExists == true) {
                        library.playlistExistsError(playlistName);
                    }

                    break;

                case "7": //edit playlist

                    System.out.print("Enter the name of the playlist to be edited : ");
                    playlistName = scanner.nextLine();

                    playlistExists = library.playlistExists(playlistName);
                    
                    if (playlistExists == true) {
                        Boolean running = true;
                        while (running == true) {

                            // Prompt a command.
                            clearScreen();
                            library.printPlaylistEditor(playlistName);
                            String commandNum = scanner.nextLine();

                            switch (commandNum) {
                                case "1": // add track

                                    if (library.getTrackList().size() > 0) {
                                        System.out.print("Enter the name of the track you wish to add : ");
                                        String trackName = scanner.nextLine();

                                        // If track already exists in playlist, notify user.
                                        if (library.trackExistsInPlaylist(trackName, playlistName) == true) {
                                            library.trackExistsInPlaylistError(trackName, playlistName);
                                        }

                                        // If the track DNE in playlist,
                                        else if (library.trackExistsInPlaylist(trackName, playlistName) == false) {
                                            int trackIndex = 0;

                                            // get the track from the library,
                                            for (Track t : library.getTrackList()) {
                                                if (t.getTitle().equals(trackName)) {
                                                    trackIndex = library.getTrackList().indexOf(t);
                                                }
                                            }

                                            // create a track clone to add,
                                            Track toAdd = library.getTrackList().get(trackIndex);

                                            // add the track to the playlist.
                                            for (Playlist p : library.getPlaylists()) {
                                                if (p.getName().equals(playlistName)) {
                                                    library.addTrackToPlaylist(toAdd, playlistName);
                                                }
                                            }
                                        }
                                    }

                                    else if (library.getTrackList().size() == 0) {
                                        library.noTracksError();
                                    }

                                    break;

                                case "2": // remove track

                                    // Check if the playlist has any tracks in it to remove.
                                    Boolean playlistHasTracks = true;
                                    for (Playlist p : library.getPlaylists()) {
                                        if ( (p.getName().equals(playlistName)) && (p.getTracks().size() == 0) ) {
                                            playlistHasTracks = false;
                                        }
                                    }

                                    if (playlistHasTracks == true) {
                                        System.out.print("Enter the name of the track you wish to remove : ");
                                        String trackName = scanner.nextLine();

                                        // If the track exists in the playlist,
                                        if (library.trackExistsInPlaylist(trackName, playlistName) == true) {

                                            // remove the track.
                                            for (Playlist p : library.getPlaylists()) {
                                                if (p.getName().equals(playlistName)) {
                                                    for (Track t : p.getTracks()) {
                                                        if (t.getTitle().equals(trackName)) {
                                                            library.removeTrackFromPlaylist(t, playlistName);
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        // If the track DNE in the playlist, notify the user.
                                        else if (library.trackExistsInPlaylist(trackName, playlistName) == false) {
                                            library.trackDNE_InPlaylistError(trackName, playlistName);
                                        }
                                    }

                                    else if (playlistHasTracks == false) {
                                        library.noTracksError();
                                    }

                                    break;

                                case "3": // edit playlist name
                                    // TODO

                                case "4": // exit editor
                                    running = false;

                            }
                        }
                    }

                    else if (playlistExists == false) {
                        library.playlistDNE_Error(playlistName);
                    }

                    break;

                case "8": // load playlist

                    System.out.print("Enter the name of the playlist to be loaded : ");
                    playlistName = scanner.nextLine();

                    playlistExists = library.playlistExists(playlistName);
                    
                    if (playlistExists == true) {
                        // Check if the provided playlist exists.
                        // playlistExists = false;
                        // while (playlistExists == false) {

                        // System.out.print("Enter the name of the playlist to be loaded : ");
                        // playlistName = scanner.nextLine();

                        // if (library.playlistExists(playlistName) == true) {
                        // playlistExists = true;
                        // }
                        // else {
                        // library.playlistDNE_Error(playlistName);
                        // }
                        // }

                        // Initialize playingIndex and playlistLength.
                        int playingIndex = 0;
                        int playlistLength = 0;

                        // Get the proper playlistLength.
                        for (Playlist p : library.getPlaylists()) {
                            if (p.getName().equals(playlistName)) {
                                playlistLength = p.getTracks().size();
                            }
                        }

                        Boolean loaded = true;
                        while (loaded == true) {

                            AudioFile playing = new AudioFile();

                            // Load the first track from the playlist into the AudioFile to be played.
                            for (Playlist p : library.getPlaylists()) {
                                if (p.getName().equals(playlistName)) {
                                    playing = new AudioFile(p.getTracks().get(playingIndex).getFileLocation());
                                }
                            }

                            while (0 <= playingIndex && playingIndex < playlistLength) {

                                clearScreen();
                                library.printPlaylistCommands();
                                String commandNum = scanner.nextLine();

                                switch (commandNum) {
                                    case "1": // play / pause

                                        if (playing.getIsPlaying()) {
                                            playing.pauseClip();
                                        } else {
                                            playing.playClip();
                                        }

                                        break;

                                    case "2": // load next track
                                        playingIndex += 1;
                                        for (Playlist p : library.getPlaylists()) {
                                            if (p.getName().equals(playlistName)) {
                                                playing = new AudioFile(
                                                        p.getTracks().get(playingIndex).getFileLocation());
                                                System.out.println("SUCCESS : Next song loaded.");
                                            }
                                        }

                                        break;

                                    case "3": // load previous track
                                        playingIndex -= 1;
                                        for (Playlist p : library.getPlaylists()) {
                                            if (p.getName().equals(playlistName)) {
                                                playing = new AudioFile(
                                                        p.getTracks().get(playingIndex).getFileLocation());
                                                System.out.println("SUCCESS : Previous song loaded.");
                                            }
                                        }

                                        break;

                                    case "4": // unload playlist
                                        loaded = false;
                                }
                            }
                        }
                    }

                    else if (playlistExists == false) {
                        library.playlistDNE_Error(playlistName);
                    }

                    break;

                case "9": // print library

                    Boolean running = true;
                    while (running == true) {

                        clearScreen();
                        library.printLibraryView();
                        String commandNum = scanner.nextLine();

                        switch (commandNum) {
                            case "1": // list all tracks
                                
                                if (library.getTrackList().size() > 0) {
                                    System.out.println();
                                    System.out.println(library.tracksToString());
                                    System.out.println();
                                }
                                else {
                                    library.noTracksError();
                                }

                                break;

                            case "2": // list all playlist

                                if (library.getPlaylists().size() > 0) {
                                    System.out.println();
                                    System.out.println(library.playlistsToString());
                                    System.out.println();
                                }
                                else {
                                    library.noPlaylistsError();
                                }

                                break;

                            case "3": // list a playlist's tracks
                                
                                System.out.print("Enter the name of the playlist to be listed : ");
                                playlistName = scanner.nextLine();

                                playlistExists = library.playlistExists(playlistName);
                                
                                if (playlistExists == true) {
                                    for (Playlist p : library.getPlaylists()) {
                                        if ((p.getName().equals(playlistName)) && (p.getTracks().size() > 0)) {
                                            p.tracksToString(playlistName);
                                        } else if ((p.getName().equals(playlistName)) && (p.getTracks().size() == 0)) {
                                            library.noTracksError();
                                        }
                                    }
                                }
                                else if (playlistExists == false) {
                                    library.playlistDNE_Error(playlistName);
                                }

                                break;

                            case "4": // exit
                                running = false;
                                break;
                           
                        }
                    }
                    break;

                case "10": // show player status

                    break;

                case "0": // quit

                    //Kill the program we're done
                    System.exit(0);

                default:

                    //Any bad info gets us returned. Sleep is bad form, but suffices for the moment
                    System.out.println("I didn't understand that");
                    Thread.sleep(2000);
                }

            }
        }

        catch (Exception e){
            //TODO(All Exceptions)
            e.printStackTrace();
        }

        scanner.close();
    }

    /**
     * Clears out the console when called. Currently does not work.
     * TODO fix this
     */
    private static void clearScreen() {

        //Brings us back to a clear console
        // System.out.flush();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();       // temporary fix
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
