package Audino;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import Audino.MediaControl.Player;
import Audino.MediaControl.Playlist;

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
        ArrayList<String> currentMenu = new ArrayList<String>();
        // Making the menu into an ArrayList<String> as to make it easier to individually modify
        currentMenu.add("1. Play/Pause loaded track");
        currentMenu.add("2. Play next track (TODO)");
        currentMenu.add("3. Play previous track (TODO)");
        currentMenu.add("4. Stop loaded track (TODO)");
        currentMenu.add("5. Load song from file");
        currentMenu.add("6. Load playlist (TODO)");
        currentMenu.add("7. Library(TODO)");
        currentMenu.add("The current loaded song is: ");
        currentMenu.add("The current playlist loaded is: ");
        currentMenu.add("The next track in the playlist is: ");
        currentMenu.add("The previous track in the playlist is: ");
        currentMenu.add("0. Quit");
        currentMenu.add("Please type the number you want and hit enter: ");

        //Init variables
        Player player = new Player();
        //Playlist playlist = new Playlist();
        Scanner scanner = new Scanner(System.in);
        int playlistIndex = 0;
        try {
            //If any file is added to the args we want to try and play it, but only the first one
            for (String arg: args){
                File input = new File(arg);
                if (input.isFile()){
                    player = new Player(input.toString());
                    player.playClip();
                    break;
                }
            }

            while (true){
                // input loop, run indefinitely until quit
                clearScreen();
                mainMenu(currentMenu);
                String line = scanner.nextLine();
                switch (line){
                case "1": //play/pause
                    if (player != null){
                        if (player.getIsPlaying()){
                            player.pauseClip();
                        }
                        else {
                            player.playClip();
                        }
                    }
                    break;
                case "2": //next track
                    break;
                case "3": //prev track
                    break;
                case "4": //stop track
                    player.stopClip();
                    break;
                case "5": //load track
                    System.out.println("Please enter filepath of track");
                    line = scanner.nextLine();
                    player = new Player(line);
                    //This may be better as a hashmap
                    currentMenu.set(7, "The current loaded song is: " + FilenameUtils.getName(line));
                    clearScreen();
                    break;
                case "6": //load playlist
                    break;
                case "7": //Open library
                    break;
                case "8":
                    break;
                case "0":
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
    }

    /**
     * Clears out the console when called. Currently does not work.
     * TODO fix this
     */
    private static void clearScreen() {
        //Brings us back to a clear console
        System.out.flush();
    }

    /**
     * Prints out the main menu. Can be retooled for any primitive menu.
     * TODO retool for any primitive menu.
     *
     * @param menu The menu to be printed out line by line
     */
    private static void mainMenu(ArrayList<String> menu){

        for (String item: menu){
            System.out.println(item);
        }
    }
}
