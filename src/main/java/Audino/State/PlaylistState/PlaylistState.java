package Audino.State.PlaylistState;

import Audino.MediaControl.Playlist;

public abstract class PlaylistState {
    Playlist playlist;

    public PlaylistState(Playlist playlist) {
        this.playlist = playlist;
    }
	
    public abstract void onNext();
    public abstract void onPrevious();

    public abstract void onNextState();
}
