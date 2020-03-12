package Audino.UI.GUI;


import java.io.File;
import java.io.IOException;

import Audino.MediaControl.Player;
import Audino.MediaControl.Track;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;

import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

public class Controller {

    private Player player = new Player();
    private Boolean isLoaded;
    private Scene scene;

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
    private MenuItem openMenuClicked;

    @FXML
    private BorderPane node;

    @FXML
    void playButtonClicked(MouseEvent event) {
        player.getState().onPlay();
        if (player.isPlaying()){
        }
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
    @FXML
    void seekBarDragged(DragEvent event){

    }
    @FXML
    void nextButtonClicked(MouseEvent event){

    }
    @FXML
    void previousButtonClicked(MouseEvent event){

    }
    @FXML
    void fastForwardButtonClicked(MouseEvent event){

    }
    @FXML
    void rewindButtonClicked(MouseEvent event){

    }
    @FXML
    void shuffleButtonClicked(MouseEvent event){

    }
    @FXML
    void repeatButtonClicked(MouseEvent event){

    }
    @FXML
    void openMenuClicked(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");
        Window stage = this.scene.getWindow();
        fileChooser.getExtensionFilters().addAll(
                                                 new ExtensionFilter("Audio Files", "*.wav", "*.mp3")
                                                 );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null){
            //DO SOMETHING HERE
            // AAAAHGHHHHAHHADFHADFGASDFASDF
        }
    }

    void initData(Player player, Scene scene){
        this.player = player;
        this.scene = scene;
    }
}
