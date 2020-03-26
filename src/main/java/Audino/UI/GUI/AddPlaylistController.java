package Audino.UI.GUI;

import Audino.MediaControl.Playlist;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AddPlaylistController extends Controller {

    private Controller mainController;

    /**
     * Called when playlistCreate button clicked: creates a new playlist.
     *
     * @param event a MouseEvent
     */
    @FXML public void playlistCreateButtonClicked(MouseEvent event) {

        String playlistName = playlistNameTextField.getText();       
        Playlist playlist = new Playlist(playlistName);
        
        addPlaylist(playlist);
        player.getLibrary().addPlaylist(playlist);
        
        updateTableView();
    }
    public AddPlaylistController(){

    }

    @FXML
    public void initialize(){
        System.out.println("I ran!");
    }

    public void setMainWindow(Controller mainController){
        this.mainController = mainController;
    }

}
