package Audino;
/*
to add:
    - ability to create playlist names with spaces
    - Player can read playlists and play them
    - User has the ability to add multiple songs to a playlist on one command
    - toStrings
*/


import java.util.ArrayList;
import java.util.Scanner;

public class Playlist {
    
    // ====================================================================== ( instance )

    private String name;
    private ArrayList<String> songs = new ArrayList<String>();
    
    // ====================================================================== ( getters )

    public String getName() {
        return this.name;
    }
    
    public ArrayList<String> getSongs() {
        ArrayList<String> cloneList = new ArrayList<String>();
        for(String s : songs) {
            cloneList.add(s);
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
    
    public void addSong(String aSong) {
        songs.add(aSong);
    }
    
    public void removeSong(String aSong) {
        songs.remove(aSong);
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
        songs = aPlaylist.getSongs();
    }
    // ====================================================================== ( toString )

    public String songsToString() {
        String allSongs = "";
        for (String s : songs) {
            allSongs += s + ", ";
        }
        return allSongs.substring(0, allSongs.length() - 2);
    }

    // ====================================================================== ( methods )

    public void userAddSongs() {
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a directory path to a song you would like to add to this playlist (blank line finish): ");
        String dirpath = keyboard.nextLine();
        
        while (dirpath != "") {
            songs.add(dirpath);
            dirpath = keyboard.nextLine();
        }
        
        System.out.println("Songs added.");
        keyboard.close();
    }

    public static Boolean validName(String aName) {
        if (aName.equals("") == true || aName.indexOf(' ') != -1) {
            return false;
        }
        else {
            return true;
        }
    }
    
    // ====================================================================== ( main method )
    public static void main(String[] args) {

        PlaylistLibrary library = new PlaylistLibrary();

        library.printCommands();

        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        while (input != "") {
            
            // If user is creating a playlist,
            if (input.length() > 16 && input.substring(0, 16).contains("create playlist ")) {

                // get the name,
                int indexOf_ = input.lastIndexOf(' ');
                String playlistName = input.substring(indexOf_ + 1);
                
                // Check if the given name is valid.
                Boolean validName = validName(playlistName);

                // Handle error of entering "create playlist."
                if (playlistName.contains("playlist")) {
                    validName = false;
                }

                // If name is invalid,
                if (validName == false) {
                    library.invalidNameError(playlistName);
                    input = keyboard.nextLine();
                    continue;
                }

                // Check if a playlist by the same name already exists.
                Boolean playlistExists = library.playlistExists(playlistName);

                // If a playlist by that name exists, notify user and prompt another command.
                if (playlistExists == true) {
                    library.playlistExistsError(playlistName);
                    input = keyboard.nextLine();
                    continue;
                }
                // If a playlist by that name DNE, create one.
                else if (playlistExists == false) {
                    library.addPlaylist(new Playlist(playlistName));
                    library.playlistCreatedSuccess(playlistName);
                    input = keyboard.nextLine();
                    continue;
                }
            }

            // If user is deleting a playlist,
            if (input.length() > 16 && input.substring(0, 16).contains("delete playlist ")) {

                // get the name,
                int indexOf_ = input.lastIndexOf(' ');
                String playlistName = input.substring(indexOf_ + 1);

                // Check if a playlist by the same name exists.
                Boolean playlistExists = library.playlistExists(playlistName);

                // If a playlist by that name DNE, notify user.
                if (playlistExists == false) {
                    library.playlistDNE_Error(playlistName);
                    input = keyboard.nextLine();
                    continue;
                 }

                // If a playlist by that name exists, delete it, notify user, and prompt another command.
                else if (playlistExists == true) {
                    library.removePlaylist(playlistName);
                    library.playlistDeletedSuccess(playlistName);
                    input = keyboard.nextLine();
                    continue;
                }
            }

             // If user is adding to a playlist,
             if (input.length() > 4 && input.substring(0,4).contains("add ")) {

                // find the first space index,
                int indexOfFirst_ = input.indexOf(" ");
                
                // then make a string of the playlist name and the song directory,
                String nameAndSong = input.substring(indexOfFirst_ + 1);

                // then find the remaining space index, 
                int indexOfSecond_ = nameAndSong.indexOf(" ");

                // and make a String for playlist name and song directory each.
                String nameOfPlaylist = nameAndSong.substring(0, indexOfSecond_);
                String songDirectory = nameAndSong.substring(indexOfSecond_ + 1);

                // Check if a playlist by the same name exists.
                Boolean playlistExists = library.playlistExists(nameOfPlaylist);
                
                // If the the song already exists in that playlist,
                if (library.songExistsInPlaylist(songDirectory, nameOfPlaylist) == true && playlistExists == true) {
                        
                    // notify the user of the error and prompt another commond.
                    library.songExistsInPlaylistError(songDirectory, nameOfPlaylist);
                    input = keyboard.nextLine();
                    continue;
                }

                // If the song does not exist in the playlist,
                else if (playlistExists == true) {

                    // add the new song to specified playlist, and prompt another command.
                    library.addSongToPlaylist(songDirectory, nameOfPlaylist);
                    library.songAddedSuccess(songDirectory, nameOfPlaylist);
                    input = keyboard.nextLine();
                    continue;
                }

                // If the playlist does not exist, notify user of the error and prompt another command.
                else {
                     library.playlistDNE_Error(nameOfPlaylist);
                     input = keyboard.nextLine();
                     continue;
                }
            }
        
            // If user is removing from a playlist,
            if (input.length() > 3 && input.substring(0,3).contains("rm ")) {

                // find the first space index,
                int indexOfFirst_ = input.indexOf(" ");
                
                // then make a string of the playlist name and the song directory,
                String nameAndSong = input.substring(indexOfFirst_ + 1);

                // then find the remaining space index,
                int indexOfSecond_ = nameAndSong.indexOf(" ");

                // and make a String for playlist name and song directory each.
                String nameOfPlaylist = nameAndSong.substring(0, indexOfSecond_);
                String songDirectory = nameAndSong.substring(indexOfSecond_ + 1);

                // Check if a playlist by the same name exists.
                Boolean playlistExists = library.playlistExists(nameOfPlaylist);

                // If the the song exists in the playlist,
                if (library.songExistsInPlaylist(songDirectory, nameOfPlaylist) == true && playlistExists == true) {
                        
                    // remove the song, and prompt another command.
                    library.removeSongFromPlaylist(songDirectory, nameOfPlaylist);
                    library.songRemovedSuccess(songDirectory, nameOfPlaylist);
                    input = keyboard.nextLine();
                    continue;

                }

                // If the song does not exist in the playlist,
                else if (playlistExists == true) {

                    // notify the user of the error, and prompt another commond.
                    library.songDNE_InPlaylistError(songDirectory, nameOfPlaylist);
                    input = keyboard.nextLine();
                    continue;
                }

                // If the playlist does not exist, notify user of the error and prompt another command.
                else {
                    library.playlistDNE_Error(nameOfPlaylist);
                    input = keyboard.nextLine();
                    continue;
                }  
            }
        
            // If user enters list,
            if (input.length() > 5 && input.substring(0,5).contains("list ")) {

                // and user specified all playlists,
                if (input.length() == 18 && input.substring(5,18).contains("all playlists")) {

                    // check if there are any playlists made,
                    if (library.getPlaylists().size() > 0) {

                        // print out all playlists, and prompt another command.
                        System.out.println(library.playlistsToString());
                        library.playlistsToStringSuccess();
                        input = keyboard.nextLine();
                        continue;
                    }

                    // If there are no playlists made,
                    else {

                        // notify the user and prompt another command.
                        library.noPlaylistsError();
                        input = keyboard.nextLine();
                        continue;
                    }
                }
                
                // If user specified a playlist,
                else {

                    // get the name of the playlist,
                    String nameOfPlaylist = input.substring(5, input.length());
                    
                    // check if the playlist exists,
                    Boolean playlistExists = library.playlistExists(nameOfPlaylist);

                    // if it does, find the playlist with the matching name,
                    if (playlistExists == true) {   
                        for (Playlist p : library.getPlaylists()) {

                            if (p.getName().equals(nameOfPlaylist)) {

                                // print out all songs, and prompt another command.
                                System.out.println(p.songsToString());
                                library.songsToStringSuccess();
                                input = keyboard.nextLine();
                                continue;
                            }
                        }
                    }
                    
                    // If the playlist does not exist,
                    else {

                        // notify user of error and prompt another commond.
                        library.playlistDNE_Error(nameOfPlaylist);
                        input = keyboard.nextLine();
                        continue;
                    }
                }
            }

            // If user enters menu,
            if (input.equals("menu")) {
                library.printCommands();
                input = keyboard.nextLine();
                continue;
            }
            
            // If user enters quit,
            if (input.equals("quit")) {
                break;
            }

            // If syntax not reconized,
            else {
                library.syntaxError();
                input = keyboard.nextLine();
                continue;
            }

        }
        
        
        
        
        
    
        
    
        keyboard.close();
    }
}
    
    
    
    
    
    
    
    
    
    
