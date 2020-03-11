package Audino.State.PlaylistState;

import Audino.MediaControl.Playlist;

public class RepeatState extends PlaylistState {

    public RepeatState(Playlist playlist) {
        super(playlist);
		//TODO Auto-generated constructor stub
	}

    @Override
    public void onNext() {
        int index = playlist.getTrackIndex();
        int newIndex = 0;
        int playlistSize = playlist.getPlaylistSize();
        if (index < playlistSize - 1) {
            newIndex = index + 1;
            playlist.setIndex(newIndex);
        }
        else {
            playlist.setIndex(newIndex); // set us to 0 and go from there
        }
    }

    @Override
    public void onPrevious() {
        int index = playlist.getTrackIndex();
        int newIndex = 0;
        if (index > 0) {
            newIndex = index - 1;
            playlist.setIndex(newIndex);
        }
        else {
            newIndex = playlist.getPlaylistSize() - 1;
            playlist.setIndex(newIndex);
        }
    }

    @Override
    public void onNextState() {
        playlist.setState(new RepeatOneState(playlist));
    }
}
