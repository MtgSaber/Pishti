package pishti.data.cardArea;

import pishti.data.card.Card;
import pishti.data.card.Pisti;
import pishti.data.player.Player;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class Captured implements CardArea {
    private ArrayList<Card> cards;
    private ArrayList<Pisti> pistis;
    private Player owner;

    public Captured(Player owner) {
        this.owner = owner;
        this.cards = new ArrayList<>();
        this.pistis = new ArrayList<>();
    }

    public ArrayList<Card> getCardsHeld() { return this.cards; }
    public ArrayList<Pisti> getPistis() { return this.pistis; }
    public Player getOwner() { return this.owner; }
}
