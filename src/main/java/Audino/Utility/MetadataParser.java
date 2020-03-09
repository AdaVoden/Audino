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
