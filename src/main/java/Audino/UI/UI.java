package Audino.UI;

import Audino.MediaControl.Player;

/**
 *
 *
 */
public abstract class UI{
    Player player;

    public UI (Player player){
        this.player = player;
    }
    public void init(){};
}
