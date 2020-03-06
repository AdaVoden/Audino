package Audino.State.PlaylistState;

import Audino.MediaControl.Playlist;

public abstract class State {
	Playlist playlist;
	
	public State(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public abstract void onNext();
	public abstract void OnPrevious();
}
