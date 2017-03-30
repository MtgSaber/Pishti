package pishti.data.cardArea;

import pishti.data.card.Card;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();

        for (int i=1; i<52; i++)
            deck.add(new Card(i));

        this.deck
    }
}
