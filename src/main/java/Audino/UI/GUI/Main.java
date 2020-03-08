package Audino.UI.GUI;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.geometry.*;
import Audino.MediaControl.*;



public class Main extends Application implements EventHandler<ActionEvent> {
	
	
	Button playButton, pauseButton, stopButton, loadButton;
	boolean isLoaded;

	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Player player = new Player();
		TextField fileDir = new TextField();
		Text instructions = new Text("Enter filepath to an audio file.");
		
		primaryStage.setTitle("Player");
		isLoaded = false;
		
		
		playButton = new Button("Play");
		pauseButton = new Button("Pause");
		stopButton = new Button("Stop");
		loadButton = new Button("Load");

		playButton.setOnAction(e -> {
			System.out.println(player.IsPlaying());
			if(player.IsPlaying() == false) {
				if(isLoaded == true) {
				try {
					player.startPlayback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				instructions.setText("Starting playback.");
				}
				else instructions.setText("No file loaded.");
			}
			else instructions.setText("Already playing.");
			});
		
		
		
		
		pauseButton.setOnAction(e -> {
			player.pausePlayback();
			instructions.setText("Paused. Press \"Play\" to resume playback.");
		});
		
		
		
		stopButton.setOnAction(e -> {
			player.stopPlayback();
			instructions.setText("Stopped.\nPress play to play loaded clip from beginning \nor load a new file.");
		});
		
		
		
		loadButton.setOnAction(e -> {
		if((player.IsPlaying() == false) && (player.IsPaused() == false)) {
		player.setTrack(new Track(fileDir.getText()));
		instructions.setText("File loaded.");
		isLoaded = true;
		}
		else instructions.setText("Click \"Stop\" before you load a new track."); ;
		});
		
		
		
		
		VBox layout = new VBox();
		layout.setAlignment(Pos. CENTER);
		layout.getChildren().addAll(playButton,pauseButton,stopButton, loadButton, fileDir, instructions);	
		
		
		
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	
	 static void main(String[] args) {
		launch(args);
		
	}


	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
