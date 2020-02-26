package Audino;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import Audino.Utility.MetadataParser;

public class MetadataParserTest {
    @Test public void testFlacParser(){
        File file = new File("src/test/resources/test_audio/testflac.flac");
        assertNotNull("Metadata should return something", MetadataParser.parseAudio(file.getAbsolutePath()));
    }
    @Test public void testOggParser(){
        File file = new File("src/test/resources/test_audio/testogg.ogg");
        assertNotNull("Metadata should return something", MetadataParser.parseAudio(file.getAbsolutePath()));
    }
    @Test public void testWavParser(){
        File file = new File("src/test/resources/test_audio/testwav.wav");
        assertNotNull("Metadata should return something", MetadataParser.parseAudio(file.getAbsolutePath()));
    }
    @Test public void testMp3Parser(){
        File file = new File("src/test/resources/test_audio/testmp3.mp3");
        assertNotNull("Metadata should return something", MetadataParser.parseAudio(file.getAbsolutePath()));
    }

}
