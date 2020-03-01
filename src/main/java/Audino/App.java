package Audino;

import Audino.MediaControl.Player;
import Audino.UI.UI;
import Audino.UI.TUI.TUI;
import Audino.UI.GUI.GUI;
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
        Player player = new Player();
        UI ui = new GUI(player);
        if (args.length > 1){
            if (args[0].equals("--text-ui")) {
                ui = new TUI(player);
            }
        }
        ui.init();
    }
}
