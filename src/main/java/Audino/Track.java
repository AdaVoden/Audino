package Audino;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.gagravarr.tika.FlacParser;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.audio.AudioParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class Track {
    private String name;
    private String album;
    private String artist;
    private double duration;
    private String fileLocation;

    public Track(String fileLocation) throws IOException {
        int locOfFileType = fileLocation.indexOf(".");
        String fileType = fileLocation.substring(locOfFileType);
        try {
            InputStream file = new FileInputStream(new File(fileLocation));
            ArrayList<String> metadata = MetadataParser.parse(file, fileType);
        }
        catch (FileNotFoundException e){
            // Do something. Not implemented.
        }

    }
}
