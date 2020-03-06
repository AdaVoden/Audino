package Audino.State.PlaylistState;

import Audino.MediaControl.Playlist;

public class DefaultState extends PlaylistState {

    public DefaultState(Playlist playlist) {
      super(playlist);
		//TODO Auto-generated constructor stub
	}

	@Override
	public void onNext() {
      int index = playlist.getTrackIndex();
      int newIndex = 0;
      int playlistSize = playlist.getPlaylistSize();
      if (index < playlistSize -1) {
          newIndex = index + 1;
          playlist.setIndex(newIndex);
      }
      // else we do nothing
	}

	@Override
	public void onPrevious() {
        int index = playlist.getTrackIndex();
        int newIndex = 0;
        if (index > 0) {
            newIndex = index - 1;
            playlist.setIndex(newIndex);
        }
	}
	
}

