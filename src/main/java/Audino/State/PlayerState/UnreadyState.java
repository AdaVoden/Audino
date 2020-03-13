package Audino.State.PlayerState;

import Audino.MediaControl.Player;

/**
 * State for when the player is ready to play but is not currently playing.
 * Example: Track is loaded, but not paused or started.
 *
 */
public class UnreadyState extends PlayerState {

    public UnreadyState(Player player) {
        super(player);
        //TODO Auto-generated constructor stub
    }

	@Override
	public void onPlay() {
		//Not ready do nothing
	}

	@Override
	public void onStop() {
		// Not ready do nothing
	}

	@Override
	public void onNext() {
		//Not ready do nothing
	}

	@Override
	public void onPrevious() {
		//not ready do nothing
	}

	@Override
	public void onFastForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRewind() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSeek(double seekTo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRepeatChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShuffleChange() {
		// TODO Auto-generated method stub

	}

}
