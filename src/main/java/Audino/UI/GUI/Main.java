package Audino.UI.GUI;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
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
    Stage primaryStage;
    Scene scene1, scene2;

    // constructor
    public Main(){
    }

    // methods
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.player = new Player();
		try {
			
			FXMLLoader loader1 = new FXMLLoader();			
			URL file = getClass().getResource("/fxml/basic_gui.fxml");
			Parent root = loader1.load(file.openStream());
			
      // FXMLLoader loader2 = new FXMLLoader();
      // URL file2 = getClass().getResource("/fxml/PlaylistNamePrompt.fxml");
      // Parent root2 = loader2.load(file2.openStream());

			Scene scene1 = new Scene(root,640,400);
			// Scene scene2 = new Scene(root2,250,100);
			
			URL stylesheet = getClass().getResource("/css/application.css");
			scene1.getStylesheets().add(stylesheet.toExternalForm());
			
			primaryStage.setScene(scene1);
			primaryStage.setTitle("Player");

      Controller controller = loader1.<Controller>getController();
            
      controller.initData(this.player, scene1, // scene2,
                          primaryStage);
            
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
	
	
	
	public void switchScene(Stage primaryStage, Scene toSwitch, String title) {
		primaryStage.setScene(toSwitch);
		primaryStage.setTitle(title);
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public Scene getScene1() {
		return this.scene1;
	}
	
	public Scene getScene2() {
		return this.scene2;
	}
}
