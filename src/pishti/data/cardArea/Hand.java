package pishti.data.cardArea;

import pishti.data.card.Card;
import pishti.data.player.Player;

import java.util.ArrayList;


/**
 * Author: Andrew Arnold (3/27/2017)
 */
public class Hand implements CardArea {
    private ArrayList<Card> cardsHeld;
    private Player owner;

    public Hand(Player owner) {
        this.cardsHeld = new ArrayList<>();
        this.owner = owner;
    }

    public ArrayList<Card> getCardsHeld() { return this.cardsHeld; }
    public Player getOwner() { return this.owner; }
}
