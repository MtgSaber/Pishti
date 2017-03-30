package pishti.data.cardArea;

import pishti.data.card.Card;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class Discard implements CardArea {
    private ArrayList<Card> cards;

    public Discard() { this.cards = new ArrayList<>(); }

    public ArrayList<Card> getCardsHeld() { return this.cards; }
}
