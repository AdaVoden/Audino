package Audino.UI.GUI;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import java.net.URL;

import Audino.MediaControl.Player;
import Audino.MediaControl.Playlist;
import Audino.MediaControl.Track;

import javafx.scene.media.AudioSpectrumListener;


import javafx.application.Platform;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

public class Controller implements Observer {

	// ============================================================== ( project variables )
	
    protected Player player = new Player();
    private Scene scene1, scene2;
    private Stage primaryStage;
    private ObservableList<Playlist> playlistsList = FXCollections.observableArrayList();
    private ObservableList<Track> trackList = FXCollections.observableArrayList();
    private final int volVizMult = 3;


    
    
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
    @FXML private TableColumn<Track, String> durationTableColumn;

    // ========================================== ( tableColumn icons )
    
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
    @FXML private MenuItem addPlaylistItem;
    @FXML private MenuItem close;
    
    // ========================================== ( playlist create )
    
    @FXML protected TextField playlistNameTextField;
    @FXML private Button playlistCreateButton;

    @FXML private Rectangle volViz;


    
    // ============================================================== ( getters )
    
    public TableView<Track> getTracksTableView() {
    	return this.tracksTableView;
    }
    
    public TableView<Playlist> getPlaylistTableView() {
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
    
    public void updateSeek() {
        double trackDur = this.player.getPlaylist().getCurrentTrack().getDuration();
        seek.setMax(trackDur);
        seek.setValue(0);
    }

    public void updateObservables() {
        ReadOnlyObjectProperty<Duration> currentTime = player.getCurrentTime();
        currentTime.addListener((observable, oldValue, newValue) -> {
            seek.setValue(newValue.toSeconds());
        });
        ObjectProperty<AudioSpectrumListener> spectrum = player.getSpectrumListener();
        spectrum.addListener((observable, oldValue, newValue) -> {
                // DO STUFF HERE
            });

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
     * Sets up the columns and rows in the tables.
     */
    public void initialize() {
      playlistTableColumn.setCellValueFactory(new PropertyValueFactory<Playlist, String>("name"));
    	songTableColumn.setCellValueFactory(new PropertyValueFactory<Track, String>("title"));
    	artistTableColumn.setCellValueFactory(new PropertyValueFactory<Track, String>("artist"));
    	durationTableColumn.setCellValueFactory(new PropertyValueFactory<Track, String>("playtime"));


      tracksTableView.setRowFactory(evs -> {
              TableRow<Track> row = new TableRow<>();
              row.setOnMouseClicked(event -> {
                      if (event.getClickCount() == 2 && (!row.isEmpty())){
                          int index = row.getIndex();
                          Playlist playlist = player.getPlaylist();
                          playlist.setIndex(index);
                          player.loadTrackFromPlaylist();
                          player.startPlayback();
                          updateSeek();
                          updateObservables();
                          playPause.setGlyphName("PAUSE");
                      }
                  });
              return row;
          });
      
      playlistTableView.setRowFactory(evs -> {
          TableRow<Playlist> row = new TableRow<>();
          row.setOnMouseClicked(event -> {
                  if (event.getClickCount() == 2 && (!row.isEmpty())){
                      int index = row.getIndex();
                      ArrayList<Playlist> playlists = player.getLibrary().getPlaylists();                      
                      player.setPlaylist(playlists.get(index));
                      trackList.clear();
                      trackList.addAll(playlists.get(index).getTracks());
                      playPause.setGlyphName("PLAY");
                      updateTableView();
                  }
              });
          return row;
      });
    }
    private void updateViz() {
    	volViz.setScaleY(this.player.getDecibel()*volVizMult);
    	System.out.println("Updating VolViz: " + volViz.toString() + " Y Scale to " + volVizMult*player.getDecibel());
    }

 
    
    // ============================================================== ( react methods )

    /**
     * Called when Player updates its media file so that the player can re-initialize itself
     * @param event
     */
    public void update(Observable obj, Object arg){
        updateSeek();
        updateObservables();
    }
    
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
        this.player.getState().onSeek(seekValue);
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

    @FXML
    void shuffleButtonClicked(MouseEvent event){
    	this.updateViz();
    }
    
    
    
    /**
     * Called when repeat button clicked: sets the playlist mode to repeat.
     * @param event a MouseEvent
     */
    @FXML void repeatButtonClicked(MouseEvent event){
        player.getState().onRepeatChange();
    }
    
    
    
    /**
     * Called when openMenu clicked: opens the open file dialogue.
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
                
                // add playlist to player library and to controller list to be displayed
                player.getLibrary().addPlaylist(playlist);
                playlistsList.add(playlist);
                
                player.setPlaylist(playlist);
                player.loadTrackFromPlaylist();
                trackList.clear();
                trackList.addAll(playlist.getTracks());
                
                updateTableView();
                updateSeek();
                updateObservables();
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
     * Closes the application when pressed
     *
     */
    @FXML void closeMenuItemClicked(){
        try {
            player.getLibrary().serialize();
        } catch (IOException e) {
            //it broke and we don't care. HDD failure?
        }
        Platform.exit();
    }
    /**
     * Called when addPlaylist icon clicked: adds a new playlist.
     * @param event an ActionEvent
     */
    @FXML void addPlaylistItemClicked(ActionEvent event) {
        try{
            URL file = getClass().getResource("/fxml/PlaylistNamePrompt.fxml");
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(file.openStream());
            AddPlaylistController controller = loader.getController();
            controller.setMainWindow(this);
            Stage stage = new Stage();
            stage.setTitle("Add Playlist");
            stage.initOwner(this.scene1.getWindow());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            // do nothing
        }
    }
}
