package Audino;

import org.junit.Test;

import Audino.MediaControl.Library;
import Audino.MediaControl.Track;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryTest {
    private ArrayList<String> getTracks() {
        File folder = new File("src/test/resources/test_audio/");
        ArrayList<String> tracks = new ArrayList<String>();
        for(File file: folder.listFiles()){
            tracks.add(file.getAbsolutePath());
        }
        return tracks;
    }
    private void deleteLibraryForTesting(){
        File lib = new File("Library.ser");
        if (lib.exists()){
            lib.delete();
        }
    }

    @Test
    public void testLibraryCreation(){
        ArrayList<String> tracks = getTracks();
        Library test = new Library();
        test.collectTracks(tracks);
        assertEquals(test.getTrackList().size(), tracks.size());
    }
    @Test
    public void testSerialization(){
        ArrayList<String> tracks = getTracks();
        deleteLibraryForTesting();
        Library test = new Library();
        test.collectTracks(tracks);
        try{
            test.serialize();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        File file = new File("Library.ser");

        assertTrue(file.exists());
        deleteLibraryForTesting();
    }
    @Test
    public void testDeserialization(){
        deleteLibraryForTesting();
        ArrayList<String> tracks = getTracks();
        Library test = new Library();
        test.collectTracks(tracks);
        try{
            test.serialize();
            File file = new File("Library.ser");
            assertTrue(file.exists());
            Library deserialized = Library.deserialize();
            ArrayList<Track> testTrackList = test.getTrackList();
            ArrayList<Track> desTrackList = deserialized.getTrackList();

            assertEquals("Should have the same size tracklist", testTrackList.size(), desTrackList.size());
            for (int i = 0; i < testTrackList.size(); i++){
                assertTrue(testTrackList.get(i).equals(desTrackList.get(i)));
            }
            deleteLibraryForTesting();

        }
        catch (IOException e){
            e.printStackTrace();
            deleteLibraryForTesting();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            deleteLibraryForTesting();
        }
    }
}
