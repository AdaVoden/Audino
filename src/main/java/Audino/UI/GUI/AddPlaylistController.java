package Audino.UI.GUI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AddPlaylistController {

    private Controller mainController;

    /**
     * Called when playlistCreate button clicked: creates a new playlist.
     *
     * @param event a MouseEvent
     */
    @FXML public void playlistCreateButtonClicked(MouseEvent event) {
        // String playlistName = playlistNameTextField.getText();
        // addPlaylist(new Playlist(playlistName));
        // updateTableView();
    }
    public AddPlaylistController(){

    }

    @FXML
    private void initialize(){
        System.out.println("I ran!");
    }

    public void setMainWindow(Controller mainController){
        this.mainController = mainController;
    }

}
