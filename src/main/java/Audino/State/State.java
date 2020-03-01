package Audino.State;

import Audino.MediaControl.Player;

/**
 * Root interface for all states.
 * Taken from:
 * https://refactoring.guru/design-patterns/state/java/example
 */
abstract class State {
    Player player;

    State(Player player) {
        this.player = player;
    }

    public abstract String onPlay();
    public abstract String onPause();
    public abstract String onStop();
    public abstract String onNext();
    public abstract String onPrevious();
}
