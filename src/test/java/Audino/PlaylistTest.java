package Audino;

import org.junit.Test;

import Audino.MediaControl.Playlist;
import Audino.MediaControl.Track;
import Audino.State.PlaylistState.RepeatOneState;
import Audino.State.PlaylistState.RepeatState;
import javafx.scene.media.Media;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlaylistTest {
    private Playlist playlistSetup(){
        File folder = new File("src/test/resources/test_audio/");
        ArrayList<File> tracks = new ArrayList<File>();
        for(File file: folder.listFiles()){
            tracks.add(file);
        }
        Playlist playlist;
        try {
            playlist = new Playlist(tracks);
            return playlist;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void testAddSingleTrack(){
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        Track testTrack = new Track(file.getAbsolutePath());
        Playlist testPlaylist = new Playlist();

        testPlaylist.addTrack(testTrack);
        assertTrue("Playlist should have more than 0 tracks", testPlaylist.getTracks().size() == 1);
    }

    @Test
    public void testAddMultipleTracks() {
        Playlist testPlaylist = playlistSetup();
        assertTrue("Playlist should have 4 tracks", testPlaylist.getTracks().size() == 4);
    }

    @Test
    public void testGetName(){
        Playlist testPlaylist = playlistSetup();
        assertTrue("Default playlist should return Default name", testPlaylist.getName().equals("Default"));

        testPlaylist.setName("Test");
        assertTrue("Changing the playlist name should change the name", testPlaylist.getName().equals("Test"));
    }

    @Test
    public void testGetPlaylistSize(){
        Playlist testPlaylist = playlistSetup();
        assertTrue("Default playlist should have 4 tracks", testPlaylist.getPlaylistSize() == 4);
    }

    @Test
    public void testShuffleBool(){
        Playlist testPlaylist = playlistSetup();
        assertTrue("Default playlist should not shuffle", !testPlaylist.getShuffle());

        testPlaylist.setShuffle(true);
        assertTrue("Playlist should shuffle now", testPlaylist.getShuffle());

        testPlaylist.setShuffle(false);
        assertTrue("Playlist should no longer shuffle", !testPlaylist.getShuffle());
    }
    @Test
    public void testDefaultIndexForward(){
        Playlist testPlaylist = playlistSetup();
        assertTrue("Default index should be 0", testPlaylist.getTrackIndex() == 0);
        testPlaylist.getState().onNext();
        assertTrue("Default state play next should be index 1", testPlaylist.getTrackIndex() == 1);
        testPlaylist.setIndex(testPlaylist.getPlaylistSize() - 1);
        testPlaylist.getState().onNext();
        assertTrue("Default state play next on last song should return last song",
                testPlaylist.getTrackIndex() ==
                   testPlaylist.getPlaylistSize() - 1);
    }

    @Test
    public void testDefaultIndexBackward() {
        Playlist testPlaylist = playlistSetup();
        assertTrue("Default index should be 0", testPlaylist.getTrackIndex() == 0);
        testPlaylist.getState().onPrevious();
        assertTrue("Default state play previous should be index 0", testPlaylist.getTrackIndex() == 0);
        testPlaylist.setIndex(testPlaylist.getPlaylistSize() - 1);
        testPlaylist.getState().onPrevious();
        assertTrue("Default state play next on last song should return last song",
                testPlaylist.getTrackIndex() == testPlaylist.getPlaylistSize() - 2);
    }

    @Test
    public void testRepeatAll(){
        Playlist testPlaylist = playlistSetup();
        testPlaylist.setState(new RepeatState(testPlaylist));

        assertTrue("Default index should be 0 on repeat state", testPlaylist.getTrackIndex() == 0);
        testPlaylist.setIndex(testPlaylist.getPlaylistSize() -1);
        testPlaylist.getState().onNext();
        assertTrue("Index should cycle back to 0 after hitting the end", testPlaylist.getTrackIndex() == 0);
        testPlaylist.getState().onPrevious();
        assertTrue("Index should cycle back to end after going back on start",
                   testPlaylist.getTrackIndex() ==
                        testPlaylist.getPlaylistSize() - 1);
    }
    @Test
    public void testRepeatOne(){
        Playlist testPlaylist = playlistSetup();
        testPlaylist.setState(new RepeatOneState(testPlaylist));

        assertTrue("Default index should be 0 on repeatOne state", testPlaylist.getTrackIndex() == 0);
        testPlaylist.getState().onNext();
        assertTrue("Index should not change on next", testPlaylist.getTrackIndex() == 0);
        testPlaylist.getState().onPrevious();
        assertTrue("Index should not change on previous", testPlaylist.getTrackIndex() == 0);
    }

}
