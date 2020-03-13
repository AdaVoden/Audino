package Audino.UI.GUI;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Audino.MediaControl.Player;
import Audino.MediaControl.Playlist;
import Audino.MediaControl.Track;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

public class Controller {

    private Player player = new Player();
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
    private MenuItem openMenuItem;

    @FXML
    private Slider seek;

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
    void seekBarDragged(MouseEvent event){
        double seekValue = seek.getValue();
        double trackDur = this.player.getPlaylist().getCurrentTrack().getDuration();
        double newVal = trackDur * seekValue;
        System.out.println(seekValue);
        System.out.println(trackDur);
        System.out.println(newVal);
        this.player.getState().onSeek(newVal);
    }
    @FXML
    void nextButtonClicked(MouseEvent event){
        player.getState().onNext();
    }
    @FXML
    void previousButtonClicked(MouseEvent event){
        player.getState().onPrevious();
    }
    @FXML
    void fastForwardButtonClicked(MouseEvent event){
        player.getState().onFastForward();
    }
    @FXML
    void rewindButtonClicked(MouseEvent event){
        player.getState().onRewind();
    }
    @FXML
    void shuffleButtonClicked(MouseEvent event){

    }
    @FXML
    void repeatButtonClicked(MouseEvent event){
        player.getState().onRepeatChange();
    }
    @FXML
    void openMenuClicked(ActionEvent event){
        Window stage = this.scene.getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");
                fileChooser.getExtensionFilters().addAll(
                                                 new ExtensionFilter("Audio Files", "*.wav", "*.mp3")
                                                 );
        List<File> selectedFile = fileChooser.showOpenMultipleDialog(stage);
        if (selectedFile != null){
            for (File f : selectedFile) {
                try {
                    Track file = new Track(f.getCanonicalPath());
                    player.getPlaylist().addTrack(file);
                    System.out.println(f.getCanonicalPath());

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
           }
            //System.out.println(player.getPlaylist().getPlaylistSize());
           player.loadTrackFromPlaylist();

        }
    }

    void initData(Player player, Scene scene){
        this.player = player;
        this.scene = scene;
    }
}
