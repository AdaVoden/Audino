package Audino;

import org.junit.Test;

import Audino.MediaControl.Player;
import Audino.MediaControl.Playlist;
import Audino.MediaControl.Track;
import Audino.State.PlayerState.PausedState;
import Audino.State.PlayerState.PlayingState;
import Audino.State.PlayerState.ReadyState;
import Audino.State.PlayerState.UnreadyState;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;

import javafx.scene.media.MediaPlayer.Status;

public class PlayerTest {
    private Player playerTestSetup(){
        File mp3 = new File("src/test/resources/test_audio/testmp3.mp3");
        File wav = new File("src/test/resources/test_audio/testwav.wav");
        Playlist playlist;
        final JFXPanel fxPanel = new JFXPanel(); //This is here because
        //MediaFX does some hidden initialization and that does not occur
        //Without this or application.launch()
        try {
            playlist = new Playlist();
            playlist.addTrack(new Track(mp3.getCanonicalPath()));
            playlist.addTrack(new Track(wav.getCanonicalPath()));
            Player player = new Player();
            player.setPlaylist(playlist);
            player.loadTrackFromPlaylist();
            return player;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testUnreadyPlay(){
        Player player = new Player();
        player.startPlayback();
        assertNull("Player should not be playing, there is no track", player.getStatus());
        assertTrue("Player should be in unready state", (player.getState() instanceof UnreadyState));
    }
    // testing callbacks is beyond me right now
    // @Test
    // public void testDefaultPlay(){
    //     Player player = playerTestSetup();

    //     assertTrue("Player should be in a ready state", (player.getState() instanceof ReadyState));


    //     player.startPlayback();
    //     assertTrue("Player should be in playing state", (player.getState() instanceof PlayingState));

    //     player.pausePlayback();
    //     assertTrue("Player should be in a paused state", (player.getState() instanceof PausedState));

    //     player.stopPlayback();
    //     assertTrue("Player should be in a stopped state", (player.getState() instanceof ReadyState));

    // }


}
