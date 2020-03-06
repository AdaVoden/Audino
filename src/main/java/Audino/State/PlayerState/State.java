package Audino.State.PlayerState;

import Audino.MediaControl.Player;

/**
 * Root interface for all states.
 * Taken from:
 * https://refactoring.guru/design-patterns/state/java/example
 */
public abstract class State {
    Player player;

    State(Player player) {
        this.player = player;
    }

    public abstract void onPlay();
    public abstract void onStop();
    public abstract void onNext();
    public abstract void onPrevious();
}
