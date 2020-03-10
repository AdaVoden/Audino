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

public class Controller {

	private Player player = new Player();
	private Boolean isLoaded;
	
    @FXML
    private Button pauseButton;

    @FXML
    private Label instructions;

    @FXML
    private Button stopButton;

    @FXML
    private Button loadButton;

    @FXML
    private TextField fileDir;

    @FXML
    private Button playButton;

    @FXML
    void playButtonClicked(ActionEvent event) {
		System.out.println(player.IsPlaying());
		if (player.IsPlaying() == false) {
			if (isLoaded == true) {
				try {
					player.getState().onPlay();
					instructions.setText("Starting playback");
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			} else {
				instructions.setText("No file loaded.");
			}
		} else {
			instructions.setText("Already playing.");
		}
	}

    @FXML
    void pauseButtonClicked(ActionEvent event) {
    	System.out.println(player.IsPaused());
    	if (player.IsPaused() == false) {
    		if (isLoaded == true) {
    			try {
    				player.getState().onPlay();
    				instructions.setText("Paused. Press \"Play\" to resume playback.");
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		} else {
    			instructions.setText("No file loaded.");
    		}
    	} else {
    		instructions.setText("Already paused.");
    	}
    }

    @FXML
    void stopButtonClicked(ActionEvent event) {
		
    	if (isLoaded == true) {
    		try {
    			player.getState().onStop();
    			instructions.setText("Stopped.\nPress play to play loaded clip from beginning \nor load a new file.");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else {
    		instructions.setText("No file loaded.");
    	}
    }

    @FXML
    void loadButtonClicked(ActionEvent event) {
    	if ((player.IsPlaying() == false) && (player.IsPaused() == false)) {
    		File file = new File(fileDir.getText());
    		
    		try {
    			player.setTrack(new Track(file.getCanonicalPath()));
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		instructions.setText("File loaded.");
    		isLoaded = true;
    		
    	} else {
    		instructions.setText("Click \"Stop\" before you load a new track.");
    	}
	}
   

}
