package Audino.UI.GUI;


import java.io.File;
import java.io.IOException;

import Audino.MediaControl.Player;
import Audino.MediaControl.Track;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

public class Controller {

    private Player player = new Player();
    private Boolean isLoaded;

    @FXML
    private Label instructions;

    @FXML
    private MaterialDesignIconView stopButton;

    @FXML
    private Button loadButton;

    @FXML
    private TextField fileDir;

    @FXML
    private MaterialDesignIconView playButton;

    @FXML
    void playButtonClicked(MouseEvent event) {
        System.out.println(player.IsPlaying());
        player.getState().onPlay();
    }

    @FXML
    void stopButtonClicked(MouseEvent event) {
        player.getState().onStop();
    }

    @FXML
    void loadButtonClicked(ActionEvent event) {
        // if ((player.IsPlaying() == false) && (player.IsPaused() == false)) {
        File file = new File(fileDir.getText());
        try {
            player.setTrack(new Track(file.getCanonicalPath()));
            instructions.setText("File loaded.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initData(Player player){
        this.player = player;
    }
}
