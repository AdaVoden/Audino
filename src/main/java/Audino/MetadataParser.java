package Audino;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import org.apache.commons.io.FilenameUtils;

import org.apache.tika.metadata.Metadata;
import org.xml.sax.ContentHandler;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.gagravarr.tika.FlacParser;
import org.gagravarr.tika.VorbisParser;
import org.apache.tika.parser.audio.AudioParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MetadataParser {
    public static Metadata parseAudio(String file) {
        try {
            InputStream input = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
            //InputStream input = new AudioInputStream(new File(file));
            String extension = FilenameUtils.getExtension(file);
            Metadata toReturn = new Metadata();
            ContentHandler handler = new DefaultHandler();
            ParseContext parseCont = new ParseContext();
            Parser parser;
            switch (extension){
            case "mp3":
                parser = new Mp3Parser();
                break;
            case "flac":
                parser = new FlacParser();
                break;
            case "ogg":
                parser = new VorbisParser();
                break;
            case "wma":
                parser = new AudioParser();
                break;
            case "wav":
                parser = new AudioParser();
                break;
            default:
                parser = new AudioParser();
            }
            parser.parse(input, handler, toReturn, parseCont);
            return toReturn;
        }
        catch (IOException e){
            //TODO Unreachable?
            e.printStackTrace();
        }
        catch (SAXException e){
            //TODO
            e.printStackTrace();
        }
        catch (TikaException e){
            //TODO
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        }
        return null;
    }
}
