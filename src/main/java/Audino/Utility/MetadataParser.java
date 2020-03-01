package Audino.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.io.FilenameUtils;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


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
    public static ArrayList<String> parseAudio(File file){
        AudioFile input;
        try {
            input = AudioFileIO.read(file);
        } catch (CannotReadException |
                 IOException |
                 TagException |
                 ReadOnlyFileException|
                 InvalidAudioFrameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        ArrayList<FieldKey> toIterate = new ArrayList<FieldKey>();
        toIterate.add(FieldKey.ARTIST);
        toIterate.add(FieldKey.ALBUM);
        toIterate.add(FieldKey.TITLE);
        toIterate.add(FieldKey.TRACK);
        toIterate.add(FieldKey.YEAR);
        ArrayList<String> toReturn = new ArrayList<String>();
        Tag tag = input.getTag();
        AudioHeader header = input.getAudioHeader();
        int trackLength = header.getTrackLength();
        for (FieldKey key : toIterate) {
            String toAdd = tag.getFirst(key);
            if (toAdd.isEmpty()){
                toAdd = "????";
            }
            toReturn.add(toAdd);
        }
        toReturn.add(trackLength + "");
        return toReturn;
    }
}
// public class MetadataParser {
//     //TODO Check file type is permissible
//     //TODO convert to taking in a File not a strong, no point in making a new file. Might as
//     // well pass around a reference.
//     /**
//      * Uses tika's metadata parsers to find and return all metadata information concerning
//      * any arbitrary supported audio file. Needs an audio file or will attempt to parse
//      * any file as an audio file.
//      *
//      * @param file The file as a string that you want to parse.
//      * @return Metadata the Tika metadata concerning the file, based on filetype
//      */
//     public static Metadata parseAudio(String file) {
//         try {
//             // Initialization blob
//             File fileToOpen = new File(file).getAbsoluteFile();
//             InputStream input = TikaInputStream.get(fileToOpen.toPath());
//             String extension = FilenameUtils.getExtension(file);
//             Metadata toReturn = new Metadata();
//             ContentHandler handler = new DefaultHandler();
//             ParseContext parseCont = new ParseContext();
//             Parser parser;
//             switch (extension){
//             case "mp3":
//                 parser = new Mp3Parser();
//                 break;
//             case "flac":
//                 parser = new FlacParser();
//                 break;
//             case "ogg":
//                 parser = new VorbisParser();
//                 break;
//             case "wav":
//                 AudioFile f = AudioFileIO.read(fileToOpen);
//                 Tag tag = f.getTag();
//                 AudioHeader header = f.getAudioHeader();

//                 System.out.println(tag.getFirst(FieldKey.ARTIST));

//                 //System.out.println(tag.getFields());
//                 // TODO WAV doesn't work properly.
//                 parser = new AudioParser();
//             default:
//                 parser = new AudioParser();
//             }
//             parser.parse(input, handler, toReturn, parseCont);
//             input.close(); //Good practice
//             return toReturn;
//         }
//         catch (IOException e){
//             //TODO Unreachable?
//             e.printStackTrace();
//         }
//         catch (SAXException e){
//             //TODO
//             e.printStackTrace();
//         }
//         catch (TikaException e){
//             //TODO
//             e.printStackTrace();
//         } catch (CannotReadException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         } catch (TagException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         } catch (ReadOnlyFileException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         } catch (InvalidAudioFrameException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//         return null;
//     }
// }
