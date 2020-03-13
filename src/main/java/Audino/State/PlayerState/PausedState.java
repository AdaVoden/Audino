package Audino.State.PlayerState;

import Audino.MediaControl.Player;

/**
 * State for when the player has a track loaded, is not playing, but has a time
 * value greater than 0
 *
 */
public class PausedState extends PlayerState {

	public PausedState(Player player) {
		super(player);
		//TODO Auto-generated constructor stub
	}

	@Override
	public void onPlay() {
		player.startPlayback();
	}
	@Override
	public void onStop() {
		player.stopPlayback();
	}

	@Override
	public void onNext() {
		player.playNext();
	}

	@Override
	public void onPrevious() {
		player.playPrevious();
	}

	@Override
	public void onFastForward() {
      player.fastForward();
	}

	@Override
	public void onRewind() {
        player.rewind();
	}

	@Override
	public void onSeek(double seekTo) {
      player.seek(seekTo);
	}

	@Override
	public void onRepeatChange() {
        player.getPlaylist().getState().onNextState();

		// TODO Auto-generated method stub

	}

	@Override
	public void onShuffleChange() {
		// TODO Auto-generated method stub

	}

}
