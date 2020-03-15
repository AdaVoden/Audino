package Audino.UI.GUI;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import Audino.MediaControl.Player;
import Audino.MediaControl.Playlist;
import Audino.MediaControl.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

public class Controller {

	// ============================================================== ( project variables )
	
    private Player player = new Player();
    private Scene scene1, scene2;
    private Stage primaryStage;
    private ObservableList<Playlist> playlistsList = FXCollections.observableArrayList();
    private ObservableList<Track> trackList = FXCollections.observableArrayList();

    
    
    // ============================================================== ( scene variables )
    // ========================================== ( root )
    
    @FXML private BorderPane node;
    @FXML private AnchorPane node2;
    
    // ========================================== ( vBoxes ) 
    
    @FXML private VBox playlists;
    @FXML private VBox trackView;
    
    // ========================================== ( tableViews )
    
    @FXML private TableView<Track> tracksTableView;    
    @FXML private TableView<Playlist> playlistTableView;
    
    // ========================================== ( tableColumns )
    
    @FXML private TableColumn<Playlist, String> playlistTableColumn;
    @FXML private TableColumn<Track, String> songTableColumn;
    @FXML private TableColumn<Track, String> artistTableColumn;
    @FXML private TableColumn<Track, Double> durationTableColumn;

    // ========================================== ( tableColumn icons )
    
    @FXML private FontAwesomeIconView addPlaylistButton;
    @FXML private MaterialDesignIconView songIcon;
    @FXML private FontAwesomeIconView artistIcon;
    @FXML private MaterialDesignIconView durationIcon; 

    // ========================================== ( slider + icons )
    
    @FXML private Slider seek;
    @FXML private MaterialDesignIconView playPause;
    @FXML private MaterialDesignIconView skipNext;
    @FXML private MaterialDesignIconView skipPrevious;
    @FXML private MaterialDesignIconView rewind;
    @FXML private MaterialDesignIconView fastForward;
    @FXML private MaterialDesignIconView shuffle;
    @FXML private MaterialDesignIconView repeat;
    
    // ========================================== ( menu )
    
    @FXML private MenuItem openMenuItem;
    @FXML private MenuItem close;
    
    // ========================================== ( playlist create )
    
    @FXML private TextField playlistNameTextField;
    @FXML private Button playlistCreateButton;
    
    // ============================================================== ( getters )
    
    public TableView<?> getTracksTableView() {
    	return this.tracksTableView;
    }
    
    public TableView<?> getPlaylistTableView() {
    	return this.playlistTableView;
    }
    
    
    
    // ============================================================== ( setters )
    
    /**
     * Adds a playlist to the ObservableArrayList used for displaying the playlists in the table.
     * @param toAdd The playlist to be added.
     */
    public void addPlaylist(Playlist toAdd) {
    	playlistsList.add(toAdd);
    }
    
    /**
     * Adds a track to the ObservableArrayList used for displaying tracks in the table.
     * @param toAdd The track to be added.
     */
    public void addTrack(Track toAdd) {
    	trackList.add(toAdd);
    }
    
    /**
     * Updates playlistTableView to include all playlists currently stored.
     */
    public void updatePlaylistTableView() {
    	playlistTableView.setItems(playlistsList);
    }
    
    /**
     * Updates tracksTableView to include all tracks currently stored.
     */
    public void updateTrackTableView() {
    	tracksTableView.setItems(trackList);
    }
    
    /**
     * Uses updatePlaylistTableView() and updateTrackTableView() to completely refresh the table view data.
     */
    public void updateTableView() {
    	this.updatePlaylistTableView();
    	this.updateTrackTableView();
    }
    
    
    
    // ============================================================== ( initialization )
    
    // ============================================================== ( methods )
    
    /**
     * Initializes with a player and scene.
     * @param player The player to be loaded
     * @param scene The Scene to be loaded
     */
    void initData(Player player, Scene scene1, // Scene scene2,
                  Stage primaryStage){
        this.player = player;
        this.scene1 = scene1;
         // this.scene2 = scene2;
        this.primaryStage = primaryStage;
    }
    
    
    /**
     * Sets up the columns in the tables.
     */
    public void initialize() {
      playlistTableColumn.setCellValueFactory(new PropertyValueFactory<Playlist, String>("name"));
    	songTableColumn.setCellValueFactory(new PropertyValueFactory<Track, String>("title"));
    	artistTableColumn.setCellValueFactory(new PropertyValueFactory<Track, String>("artist"));
    	durationTableColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("duration"));


    }

    
    
    
    // ============================================================== ( methods )
    
  //   public void switchScene(Stage primaryStage, Scene toSwitch, String title) {
	// 	primaryStage.setScene(toSwitch);
	// 	primaryStage.setTitle(title);
	// }
    
    // ============================================================== ( react methods )
    
    // ============================================================== ( react methods )
    
    /**
     * Called when play button clicked: plays/pauses song,
     * and updates button to indicate state.
     * @param event a MouseEvent
     */
    @FXML void playButtonClicked(MouseEvent event) {
        player.getState().onPlay();

        if (playPause.getGlyphName().equals("PLAY")) {
        	playPause.setGlyphName("PAUSE");
        } else if (playPause.getGlyphName().contentEquals("PAUSE")) {
        	playPause.setGlyphName("PLAY");
        }
    }

    
    /**
     * Called when stop button clicked: stops song.
     * @param event a MouseEvent
     */
    @FXML void stopButtonClicked(MouseEvent event) {
        player.getState().onStop();
    }
    
    
    
    /**
     * Called when seek bar dragged: seeks song to selected duration.
     * @param event a MouseEvent
     */
    @FXML void seekBarDragged(MouseEvent event){
        double seekValue = seek.getValue();
        double trackDur = this.player.getPlaylist().getCurrentTrack().getDuration();
        double newVal = trackDur * seekValue;
        System.out.println(seekValue);
        System.out.println(trackDur);
        System.out.println(newVal);
        this.player.getState().onSeek(newVal);
    }
    
    
    
    /**
     * Called when next button clicked: plays next song in queue.
     * @param event a MouseEvent
     */
    @FXML void nextButtonClicked(MouseEvent event){
        player.getState().onNext();
    }
    
    
    
    /**
     * Called when previous button clicked: plays previous song in queue.
     * @param event a MouseEvent
     */
    @FXML void previousButtonClicked(MouseEvent event){
        player.getState().onPrevious();
    }
    
    
    
    /**
     * Called when ff button clicked: fastforwards song.
     * @param event a MouseEvent
     */
    @FXML void fastForwardButtonClicked(MouseEvent event){
        player.getState().onFastForward();
    }
    
    
    
    /**
     * Called when rw button clicked: rewinds song.
     * @param event a MouseEvent
     */
    @FXML void rewindButtonClicked(MouseEvent event){
        player.getState().onRewind();
    }
    
    
    
    /**
     * Called when shuffle button clicked: sets playlist mode to shuffle.
     * @param event a MouseEvent
     */
    @FXML void shuffleButtonClicked(MouseEvent event){

    }
    
    
    
    /**
     * Called when repeat button clicked: sets the playlist mode to repeat.
     * @param event a MouseEvent
     */
    @FXML void repeatButtonClicked(MouseEvent event){
        player.getState().onRepeatChange();
    }
    
    
    
    /**
     * Called when openMenu clicked: opens the menu.
     * @param event an ActionEvent
     */
    @FXML void openMenuClicked(ActionEvent event){
        Window stage = this.scene1.getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");
        fileChooser.getExtensionFilters().addAll(
                                                 new ExtensionFilter("Audio Files", "*.wav", "*.mp3")
                                                 );
        List<File> selectedFile = fileChooser.showOpenMultipleDialog(stage);
        if (selectedFile != null){
            try {
                Playlist playlist = new Playlist(selectedFile);
                player.setPlaylist(playlist);
                player.loadTrackFromPlaylist();
                trackList.clear();
                trackList.addAll(playlist.getTracks());
                playlistsList.add(playlist);
                updateTableView();
                playPause.setGlyphName("PLAY");
            }
            catch (IOException e) {
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
                //TODO something here
            }
        }
    }
    /**
     * Called when addPlaylist icon clicked: adds a new playlist.
     * @param event an ActionEvent
     */
    @FXML void addPlaylistButtonClicked(ActionEvent event) {
    	
    	// switchScene(primaryStage, scene2, "Add a Playlist");
   
    }

    
    /**
     * Called when playlistCreate button clicked: creates a new playlist.
     * @param event a MouseEvent
     */
    @FXML void playlistCreateButtonClicked(MouseEvent event) {
    	String playlistName = playlistNameTextField.getText();
    	addPlaylist(new Playlist(playlistName));
    	updateTableView();
    }
}
