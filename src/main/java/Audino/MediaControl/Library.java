package Audino.MediaControl;

import Audino.MediaControl.Track;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.lang.ClassNotFoundException;

import java.util.ArrayList;

/**
 * Disk-writeable and readable permanent storage for all tracks that are
 * consumed from a user-defined folder list. Saved to disk to prevent loss and
 * re-iteration through potentially vast amounts of track data. Only one file
 * name is allowed to prevent duplication.
 *
 * Written by: Harlan Shaw
 * Email: harlan.shaw@ucalgary.ca
 */
public class Library implements Serializable {
    private final ArrayList<Track> trackList = new ArrayList<Track>();
    private static final long serialVersionUID = 4L;
    private static final String fileName = "Library.ser";

    /**
     * Writes any given library file to disk with library name for re-use.
     *
     * @param obj Library that's going to be written to disk.
     */
    public void serialize(final java.io.Serializable obj) throws IOException{
        try {
            final ObjectOutputStream libOut = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
            libOut.writeObject(obj);
            libOut.close();
        }
        catch(final IOException e){
            // TODO
            e.printStackTrace();
        }
    }
    /**
     * Reloads the library data into memory.
     *
     * @return Library the library.ser file that was written to disk
     */
    public static Library deserialize() throws IOException, ClassNotFoundException{
        try {
            ObjectInputStream lib = new ObjectInputStream(new FileInputStream(new File(fileName)));

            Library toReturn = (Library) lib.readObject();
            lib.close();
            return toReturn;
        }
        catch (final IOException e){
            // TODO
            e.printStackTrace();
        }
        catch (final ClassNotFoundException e){
            // TODO
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Finds tracks from user-defined folder list for import into library.
     *
     * @param folderList List of folders that
     */
    public void collectTracks(final ArrayList<String> folderList){
        for (final String file: folderList) {
            final Track newTrack = new Track(file);
            trackList.add(newTrack);
            // TODO Skip/Retry/Ignore bad trackfile?
        }
    }
    /**
     * Getter for trackList. Currently is a privacy leak.
     *
     * @param
     * @return ArrayList<Track> Returns the tracks stored in the library
     */
    public ArrayList<Track> getTrackList(){
        return trackList; //TODO Privacy leak.
    }

}
