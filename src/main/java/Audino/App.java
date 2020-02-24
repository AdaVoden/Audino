package Audino;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import Audino.AudioFile;
import org.apache.commons.io.FilenameUtils;
//import Audino.Playlist;

public class App {

    public static void main(String[] args){
        ArrayList<String> currentMenu = new ArrayList<String>();

        currentMenu.add("1. Play/Pause loaded track");
        currentMenu.add("2. Play next track");
        currentMenu.add("3. Play previous track");
        currentMenu.add("4. Stop loaded track");
        currentMenu.add("5. Load song from file");
        currentMenu.add("6. Load playlist (TODO)");
        currentMenu.add("7. Library(TODO)");
        currentMenu.add("The current loaded song is: ");
        currentMenu.add("The current playlist loaded is:");
        currentMenu.add("The next track in the playlist is: ");
        currentMenu.add("The previous track in the playlist is: ");
        currentMenu.add("0. Quit");
        currentMenu.add("Please type the number you want and hit enter: ");


        AudioFile track = new AudioFile();
        //Playlist playlist = new Playlist();
        try {
            for (String arg: args){
                File input = new File(arg);
                if (input.isFile()){
                    // Here we play a file immediately if it's been thrown at the program
                    track = new AudioFile(input.toString());
                    track.playClip();
                    break;
                }
            }

            Scanner scanner = new Scanner(System.in);
            while (true){
                // input loop, run indefinitely until quit
                clearScreen();
                mainMenu(currentMenu);
                String line = scanner.nextLine();
                switch (line){
                case "1": //play/pause
                    if (track != null){
                        if (track.getIsPlaying()){
                            track.pauseClip();
                        }
                        else {
                            track.playClip();
                        }
                    }
                    break;
                case "2": //next track
                    break;
                case "3": //prev track
                    break;
                case "4": //stop track
                    track.stopClip();
                    break;
                case "5": //load track
                    System.out.println("Please enter filepath of track");
                    line = scanner.nextLine();
                    track = new AudioFile(line);
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
                    System.exit(0);
                default:
                    System.out.println("I didn't understand that");
                    Thread.sleep(2000);
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void clearScreen() {
        //Brings us back to a clear console
        System.out.flush();
    }
    private static void mainMenu(ArrayList<String> menu){

        for (String item: menu){
            System.out.println(item);
        }
    }
}
