package Audino;

import java.util.ArrayList;
import Audino.MediaControl.Playlist;

public class PlaylistLibrary {
    
    // ====================================================================== ( instance )

    private ArrayList<Playlist> Library = new ArrayList<Playlist>();

    // ====================================================================== ( getters )

    public ArrayList<Playlist> getPlaylists() {
        ArrayList<Playlist> cloneList = new ArrayList<Playlist>();
        for(Playlist p : Library) {
            cloneList.add(new Playlist(p));
        }
        return cloneList;
    }

    // ====================================================================== ( setters )

    public void addPlaylist(Playlist aPlaylist) {
        Library.add(new Playlist(aPlaylist));
    }

    public void removePlaylist(String aPlaylist) {
        boolean match = false;
        int index = 0;
        for (Playlist p : Library) {
            if (p.getName().equals(aPlaylist)) {
                index = Library.indexOf(p);
                match = true;
            }
        }
        if (match == true) {
            Library.remove(index);
        }
        else if (match == false) {
            System.out.println("No match was found.");
        }
    }

    public void addSongToPlaylist(String aSong, String aPlaylist) {
        for (Playlist p : Library) {
            if (p.getName().equals(aPlaylist)) {
                p.addSong(aSong);
            }
        }
    }

    public void removeSongFromPlaylist(String aSong, String aPlaylist) {
        for (Playlist p : Library) {
            if (p.getName().equals(aPlaylist)) {
                p.removeSong(aSong);
            }
        }
    }

    // ====================================================================== ( constructors )
    // ============================================== from new

    public PlaylistLibrary() {
        
    }

    // ============================================== copy

    public PlaylistLibrary(PlaylistLibrary aLibrary) {
        ArrayList<Playlist> cloneList = new ArrayList<Playlist>();
        for(Playlist p : Library) {
            cloneList.add(new Playlist(p));
        }
    }

    // ====================================================================== ( toString )

    public String playlistsToString() {
        String allPlaylists = "";
        for (Playlist p : Library) {
            allPlaylists += p.getName() + ", ";
        }
        return allPlaylists.substring(0, allPlaylists.length() - 2);
    }

    // ====================================================================== ( methods )
    // ============================================== existence booleans
    public boolean playlistExists(String aPlaylist) {
        for(Playlist p : Library) {
            if (p.getName().equals(aPlaylist)) {
                return true;
            }
        }
        return false;
    }

    public boolean songExistsInPlaylist(String aSong, String aPlaylist) {
        for(Playlist p : Library) {
            if (p.getName().equals(aPlaylist)) {
                for(String s : p.getSongs()) {
                    if (s.equals(aSong)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // ============================================== command menu
    public void printCommands() {
        System.out.println("========================- ( Playlist Commands ) -==========================");
        System.out.println("create playlist [name]          -> creates new playlist (NO SPACES IN NAME)");
        System.out.println("delete playlist [name]          -> deletes a playlist"                      );
        System.out.println("add [playlist_name] [song_dir]  -> adds to playlist"                        );
        System.out.println("rm [playlist_name] [song_dir]   -> removes from a playlist"                 );
        System.out.println("play [name] <mode>              -> plays playlist in selected mode"         );
        System.out.println("                                      <> : n/a , shuffle, repeat"           );
        System.out.println("list all playlists              -> lists all playlists"                     );
        System.out.println("list [playlist_name]            -> lists songs in specified playlist"       );
        System.out.println("menu                            -> bring up the command menu"               );
        System.out.println("quit                            -> terminates program"                      );
        System.out.println("======================- ENTER A BLANK LINE TO QUIT -=======================");
        System.out.println();
        System.out.print("Enter a command: ");
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
        System.out.print("Enter another command : ");
    
    }

    public void songExistsInPlaylistError(String aSong, String aPlaylist) {
        
        System.out.println("ERROR : \"" + aSong + "\" already exists in \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void songDNE_InPlaylistError(String aSong, String aPlaylist) {
        
        System.out.println("ERROR : \"" + aSong + "\" does not exist in \"" + aPlaylist + ".\"");
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

    public void songAddedSuccess(String aSong, String aPlaylist) {
        
        System.out.println("SUCCESS : \"" + aSong + "\" has been added to \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void songRemovedSuccess(String aSong, String aPlaylist) {
        
        System.out.println("SUCCESS : \"" + aSong + "\" has been removed from \"" + aPlaylist + ".\"");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void playlistsToStringSuccess() {
        
        System.out.println("SUCCESS : all playlists listed.");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }

    public void songsToStringSuccess() {

        System.out.println("SUCCESS : all songs listed.");
        System.out.println();
        System.out.print("Enter another command : ");
    
    }
}
