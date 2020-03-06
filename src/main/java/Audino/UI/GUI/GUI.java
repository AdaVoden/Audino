package Audino.UI.GUI;

import Audino.MediaControl.Player;
import Audino.UI.UI;

import javafx.application.Application;
import javafx.stage.Stage;



/**
 *
 *
 */
public class GUI extends Application implements UI {
    private Player player;
    /**
     *
     *
     */
    public GUI(Player player) {
        this.player = player;
    }
    /**
     *
     *
     */
    public void init(){
        Stage stage = new Stage();
        start(stage);
    }
    /**
     *
     *
     */
    public void start(Stage stage){
        //TODO do something
    }
}
