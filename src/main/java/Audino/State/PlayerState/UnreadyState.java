package Audino.State.PlayerState;

import Audino.MediaControl.Player;

/**
 * State for when the player is ready to play but is not currently playing.
 * Example: Track is loaded, but not paused or started.
 *
 */
public class UnreadyState extends State {

    public UnreadyState(Player player) {
        super(player);
        //TODO Auto-generated constructor stub
    }

	@Override
	public void onPlay() {
		//Npt ready do nothing
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

}
