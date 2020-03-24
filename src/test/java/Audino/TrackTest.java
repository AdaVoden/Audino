package Audino;

import org.junit.Test;

import Audino.MediaControl.Track;
import javafx.scene.media.Media;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class TrackTest {
    private Track setupTestTrack(){
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        Track testTrack;
        try {
            testTrack = new Track(file.getCanonicalPath());
            return testTrack;
        } catch (IOException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void testFlacTrack(){
        File file = new File("src/test/resources/test_audio/testflac.flac");
        Track testTrack = new Track(file.getAbsolutePath());
        assertNotNull("Title should not be null", testTrack.getTitle());
        assertNotNull("Artist should not be null", testTrack.getArtist());
        assertNotNull("Album should not be null", testTrack.getAlbum());
        assertNotNull("Duration should not be null", testTrack.getDuration());
    }
    @Test
    public void testOggTrack() {
        File file = new File("src/test/resources/test_audio/testogg.ogg");
        Track testTrack = new Track(file.getAbsolutePath());
        assertNotNull("Title should not be null", testTrack.getTitle());
        assertNotNull("Artist should not be null", testTrack.getArtist());
        assertNotNull("Album should not be null", testTrack.getAlbum());
        assertNotNull("Duration should not be null", testTrack.getDuration());
    }
    @Test
    public void testWavTrack() {
        File file = new File("src/test/resources/test_audio/testwav.wav");
        Track testTrack = new Track(file.getAbsolutePath());
        assertNotNull("Title should not be null", testTrack.getTitle());
        assertNotNull("Artist should not be null", testTrack.getArtist());
        assertNotNull("Album should not be null", testTrack.getAlbum());
        assertNotNull("Duration should not be null", testTrack.getDuration());
    }
    @Test
    public void testMp3Track() {
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        Track testTrack = new Track(file.getAbsolutePath());
        assertNotNull("Title should not be null", testTrack.getTitle());
        assertNotNull("Artist should not be null", testTrack.getArtist());
        assertNotNull("Album should not be null", testTrack.getAlbum());
        assertNotNull("Duration should not be null", testTrack.getDuration());
    }

    @Test
    public void testTrackEquals(){
        Track one = setupTestTrack();
        Track two = setupTestTrack();
        assertTrue(one.equals(two));
    }
    @Test
    public void testGetAlbum(){
        Track test = setupTestTrack();
        assertTrue("test drips".equals(test.getAlbum()));
    }

    @Test
    public void testGetArtist() {
        Track test = setupTestTrack();
        assertTrue("cfork.net".equals(test.getArtist()));
    }

    @Test
    public void testGetDuration() {
        Track test = setupTestTrack();
        assertTrue(0.0 == test.getDuration());
    }

    @Test
    public void testGetFile() {
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        Track test = setupTestTrack();
        try {
            assertTrue(file.getCanonicalPath().equals(test.getFile().getCanonicalPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
