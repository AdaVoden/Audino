package Audino;

import java.io.File;
import java.io.Serializable;
import Audino.Track;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ClassNotFoundException;

public class Library implements Serializable {
    private ArrayList<Track> trackList = new ArrayList<Track>();
    private static final long serialVersionUID = 4L;
    private static final String fileName = "Library.ser";

    public void Serialize(java.io.Serializable obj) throws IOException{
        try {
            ObjectOutputStream libOut = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
            libOut.writeObject(obj);
            libOut.close();
        }
        catch(IOException e){
            // TODO
            e.printStackTrace();
        }
    }
    public static Object Deserialize() throws IOException, ClassNotFoundException{
        try {
            ObjectInputStream lib = new ObjectInputStream(new FileInputStream(new File(fileName)));
            lib.close();
            return lib.readObject();
        }
        catch (IOException e){
            // TODO
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            // TODO
            e.printStackTrace();
        }
        return null;
    }
    public void collectTracks(ArrayList<String> fileList){
        for (String file: fileList) {
            Track newTrack = new Track(file);
            trackList.add(newTrack);
            // TODO Skip/Retry/Ignore bad trackfile?
        }
    }
    public ArrayList<Track> getTrackList(){
        return trackList; //TODO Privacy leak.
    }

}
