package Audino;

import org.junit.Test;

import Audino.MediaControl.Track;

import static org.junit.Assert.*;

import java.io.File;

public class TrackTest {
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
    // @Test
    // public void testWavTrack() {
    //     File file = new File("src/test/resources/test_audio/testwav.wav");
    //     Track testTrack = new Track(file.getAbsolutePath());
    //     assertNotNull("Title should not be null", testTrack.getTitle());
    //     assertNotNull("Artist should not be null", testTrack.getArtist());
    //     assertNotNull("Album should not be null", testTrack.getAlbum());
    //     assertNotNull("Duration should not be null", testTrack.getDuration());
    // }
    @Test
    public void testMp3Track() {
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        Track testTrack = new Track(file.getAbsolutePath());
        assertNotNull("Title should not be null", testTrack.getTitle());
        assertNotNull("Artist should not be null", testTrack.getArtist());
        assertNotNull("Album should not be null", testTrack.getAlbum());
        assertNotNull("Duration should not be null", testTrack.getDuration());
    }

}
