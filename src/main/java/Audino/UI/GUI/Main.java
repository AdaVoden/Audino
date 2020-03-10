package Audino.UI.GUI;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.geometry.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Audino.MediaControl.*;
import Audino.UI.UI;



public class Main extends Application implements EventHandler<ActionEvent>, UI {

	// instance
    Button playButton, pauseButton, stopButton, loadButton;
    boolean isLoaded;
    Player player;

    // constructor
    public Main(){
    }

    // methods
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.player = new Player();
		try {
			AnchorPane root = new AnchorPane();
			
			FXMLLoader loader = new FXMLLoader();
			root = (AnchorPane)loader.load(new FileInputStream("src/main/java/Audino/UI/GUI/Layout.fxml"));
			
			Scene scene = new Scene(root,326,124);
			//scene.getStylesheets().add(getClass().getResource("src/main/java/Audino/UI/GUI/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Player");

            Controller controller = loader.<Controller>getController();

            controller.initData(this.player);

			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(String[] args) {
		launch(args);
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
//		
//		
//		
//		this.player = new Player();
//		TextField fileDir = new TextField();
//		Text instructions = new Text("Enter filepath to an audio file.");
//
//		primaryStage.setTitle("Player");
//		isLoaded = false;
//
//
//		playButton = new Button("Play");
//		pauseButton = new Button("Pause");
//		stopButton = new Button("Stop");
//		loadButton = new Button("Load");
//
//		playButton.setOnAction(e -> {
//			System.out.println(player.IsPlaying());
//      //			if(player.IsPlaying() == false) {
//			//	if(isLoaded == true) {
//			//	try {
//
//					player.getState().onPlay();
//          //	} catch (Exception e1) {
//          //		e1.printStackTrace();
//          //	}
//				instructions.setText("Starting playback.");
//        //	}
//        //	else instructions.setText("No file loaded.");
//        //	}
//        //else instructions.setText("Already playing.");
//			});
//
//
//
//
//		pauseButton.setOnAction(e -> {
//			player.getState().onPlay();
//			instructions.setText("Paused. Press \"Play\" to resume playback.");
//		});
//
//
//
//		stopButton.setOnAction(e -> {
//			player.getState().onStop();
//			instructions.setText("Stopped.\nPress play to play loaded clip from beginning \nor load a new file.");
//		});
//
//
//
//		loadButton.setOnAction(e -> {
//            //if((player.IsPlaying() == false) && (player.IsPaused() == false)) {
//            File file = new File(fileDir.getText());
//		try {
//			player.setTrack(new Track(file.getCanonicalPath()));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		instructions.setText("File loaded.");
//		isLoaded = true;
//		//}
//		//else instructions.setText("Click \"Stop\" before you load a new track."); ;
//		});
//		
//		
//		
//		
//		VBox layout = new VBox();
//		layout.setAlignment(Pos. CENTER);
//		layout.getChildren().addAll(playButton,pauseButton,stopButton, loadButton, fileDir, instructions);	
//		
//		
//		
//		
//		Scene scene = new Scene(layout, 300, 250);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		
//		
//	}
//	@Override
//	public void handle(ActionEvent event) {
//		// TODO Auto-generated method stub
//		
//	}
//  @Override
//  public void initialize(String[] args) {
//      launch(args);
//  }
//    @Override
//    public void init(){
//
//    }

