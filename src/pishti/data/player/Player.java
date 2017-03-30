package pishti.data.player;

import pishti.data.cardArea.Captured;
import pishti.data.cardArea.Hand;

/**
 * Author: Andrew Arnold (3/27/2017)
 */
public class Player {
    private Hand hand;
    private Captured capturedCards;

    public Player() {
        this.hand = new Hand(this);
        this.capturedCards = new Captured(this);
    }

    public Hand getHand() { return this.hand; }
    public Captured getCapturedCards() { return this.capturedCards; }
}
