package Audino;

import Audino.Track;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ClassNotFoundException;
import java.util.ArrayList;

public class Library implements Serializable {
    private final ArrayList<Track> trackList = new ArrayList<Track>();
    private static final long serialVersionUID = 4L;
    private static final String fileName = "Library.ser";

    public void Serialize(final java.io.Serializable obj) throws IOException{
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
    public static Library Deserialize() throws IOException, ClassNotFoundException{
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
    public void collectTracks(final ArrayList<String> fileList){
        for (final String file: fileList) {
            final Track newTrack = new Track(file);
            trackList.add(newTrack);
            // TODO Skip/Retry/Ignore bad trackfile?
        }
    }
    public ArrayList<Track> getTrackList(){
        return trackList; //TODO Privacy leak.
    }

}
