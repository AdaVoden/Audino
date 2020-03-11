package Audino;

import Audino.MediaControl.Player;
import Audino.UI.UI;
import Audino.UI.TUI.TUI;
import Audino.UI.GUI.Main;
/**
 * Runner class. This is the main code that initializes and runs the project.
 *
 * Written by: Harlan Shaw
 * Email: harlan.shaw@ucalgary.ca
 */
public class App {
    /**
     * Main entry point into application, loads a new player and then initializes the UI
     * Checks whether or not to launch with a text UI or a graphical UI based on inputs
     *
     *@param args The list of commandline arguments pass into the application
     */
    public static void main(String[] args) {
        UI ui = null;

        if (args.length > 0) {
            if (args[0].equals("--text-ui")){
                ui = new TUI();
            }
        }
        else{
            ui = new Main();
        }
        ui.initialize(args);
    }
}
