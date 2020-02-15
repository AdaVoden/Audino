import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.tika.metadata.Metadata;
import org.xml.sax.ContentHandler;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MetadataParser {
    public static ArrayList<String> parse(InputStream file, String extension) {
        ArrayList<String> toReturn;
        switch (extension){
        case ".mp3":
            toReturn = parseMP3(file);
            break;
        case ".flac":
            toREturn = parseFLAC(file);
            break;
        case ".ogg":
            toReturn = parseOGG(file);
            break;
        case ".wma":
            toReturn = parseWMA(file);
            break;
        case ".wav":
            toReturn = parseWAV(file);
            break;
        }
    }

    public static ArrayList<String> parseMP3(InputStream file){
        return null;
    }
    public static ArrayList<String> parseFLAC(InputStream file){
        return null;

    }

    public static ArrayList<String> parseOGG(InputStream file) {
        return null;

    }

    public static ArrayList<String> parseWMA(InputStream file) {
        return null;

    }

    public static ArrayList<String> parseWAV(InputStream file) {
        return null;

    }

}
