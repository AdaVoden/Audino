package Audino.Utility;

import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
// import org.apache.tika.io.TikaInputStream;
import org.apache.tika.parser.audio.AudioParser;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ContentHandler;

import org.gagravarr.tika.FlacParser;
import org.gagravarr.tika.VorbisParser;

/**
 * Utility module designed not to be instantiated but to have its methods run arbitrarily
 *
 * Parses and returns all metadata about any arbitrary supported audio file that is passed.
 *
 * Refer to tika documentation on what is entirely returned with the metadata object, per
 * audio file type and parser
 *
 * Written by: Harlan Shaw
 * Email: harlan.shaw@ucalgary.ca
 */
public class MetadataParser {
    //TODO Check file type is permissible
    //TODO convert to taking in a File not a strong, no point in making a new file. Might as
    // well pass around a reference.
    /**
     * Uses tika's metadata parsers to find and return all metadata information concerning
     * any arbitrary supported audio file. Needs an audio file or will attempt to parse
     * any file as an audio file.
     *
     * @param file The file as a string that you want to parse.
     * @return Metadata the Tika metadata concerning the file, based on filetype
     */
    public static Metadata parseAudio(String file) {
        try {
            // Initialization blob
            File fileToOpen = new File(file).getAbsoluteFile();
            InputStream input = TikaInputStream.get(fileToOpen.toPath());
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
            case "wav":
                // TODO WAV doesn't work properly.
                parser = new AudioParser();
            default:
                parser = new AudioParser();
            }
            parser.parse(input, handler, toReturn, parseCont);
            input.close(); //Good practice
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
        return null;
    }
}
