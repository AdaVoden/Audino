package Audino.State.PlaylistState;

import Audino.MediaControl.Playlist;

public class RepeatOneState extends PlaylistState {
    public RepeatOneState(Playlist playlist) {
        super(playlist);
		//TODO Auto-generated constructor stub
	}
    @Override
    public void onNext() {
        // do nothing
    }

    @Override
    public void onPrevious() {
        // do nothing
    }

    @Override
    public void onNextState() {
        playlist.setState(new DefaultState(playlist));
    }

}
