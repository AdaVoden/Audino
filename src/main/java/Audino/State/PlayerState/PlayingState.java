package Audino.State.PlayerState;

import Audino.MediaControl.Player;

/**
 * State for when the player has a track loaded and is playing it.
 *
 */
public class PlayingState extends State {

	public PlayingState(Player player) {
		super(player);
		//TODO Auto-generated constructor stub
	}

	@Override
	public void onPlay() {
		player.pausePlayback();
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

}
