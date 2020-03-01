package Audino;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import Audino.Utility.MetadataParser;

public class MetadataParserTest {
    @Test public void testFlacParser(){
        File file = new File("src/test/resources/test_audio/testflac.flac");
        ArrayList<String> fromParser = MetadataParser.parseAudio(file);
        int arrayLength = fromParser.size();
        assertNotNull("Metadata should return something", fromParser);
        assertEquals(arrayLength, 6);

        for (String key : fromParser) {
            assertNotNull("Nothing should be null", key);
        }
    }
    @Test public void testOggParser(){
        File file = new File("src/test/resources/test_audio/testogg.ogg");
        ArrayList<String> fromParser = MetadataParser.parseAudio(file);
        int arrayLength = fromParser.size();
        assertNotNull("Metadata should return something", fromParser);
        assertEquals(arrayLength, 6);

        for (String key : fromParser) {
            assertNotNull("Nothing should be null", key);
        }
    }
    @Test public void testWavParser(){
        File file = new File("src/test/resources/test_audio/testwav.wav");
        ArrayList<String> fromParser = MetadataParser.parseAudio(file);
        int arrayLength = fromParser.size();
        assertNotNull("Metadata should return something", fromParser);
        assertEquals(arrayLength, 6);

        for (String key : fromParser) {
            assertNotNull("Nothing should be null", key);
        }
    }
    @Test public void testMp3Parser(){
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        ArrayList<String> fromParser = MetadataParser.parseAudio(file);
        int arrayLength = fromParser.size();
        assertNotNull("Metadata should return something", fromParser);
        assertEquals(arrayLength, 6);

        for (String key : fromParser) {
            assertNotNull("Nothing should be null", key);
        }
    }

}
