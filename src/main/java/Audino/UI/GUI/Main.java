package Audino.UI.GUI;

import java.io.IOException;
import java.net.URL;

import Audino.MediaControl.Player;
import Audino.UI.UI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements UI {

    // instance
    Player player;

    // constructor
    public Main() {
    }

    // methods
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.player = new Player();
        try {

            FXMLLoader loader1 = new FXMLLoader();
            URL file = getClass().getResource("/fxml/basic_gui.fxml");
            Parent root = loader1.load(file.openStream());

            Scene scene = new Scene(root);

            URL stylesheet = getClass().getResource("/css/application.css");
            scene.getStylesheets().add(stylesheet.toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Player");
            Controller controller = loader1.<Controller>getController();

            controller.initData(this.player, scene);
            player.addObserver(controller);
            primaryStage.onCloseRequestProperty().setValue(e -> Platform.exit());

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(String[] args) {
        launch(args);

    }

}
