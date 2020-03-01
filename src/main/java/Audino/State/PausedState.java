package Audino.State;

import Audino.MediaControl.Player;

/**
 * State for when the player has a track loaded, is not playing, but has a time
 * value greater than 0
 *
 */
public class PausedState extends State {

	PausedState(Player player) {
		super(player);
		//TODO Auto-generated constructor stub
	}

	@Override
	public String onPlay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onPause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onStop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onPrevious() {
		// TODO Auto-generated method stub
		return null;
	}

}
