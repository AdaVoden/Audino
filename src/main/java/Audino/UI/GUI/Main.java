package Audino.UI.GUI;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

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
			
      FXMLLoader loader = new FXMLLoader();
      URL file = getClass().getResource("/fxml/basic_gui.fxml");
      Parent root = loader.load(file.openStream());

			Scene scene = new Scene(root,640,400);
      URL stylesheet = getClass().getResource("/css/application.css");
      scene.getStylesheets().add(stylesheet.toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Player");

            Controller controller = loader.<Controller>getController();

            controller.initData(this.player, scene);

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
