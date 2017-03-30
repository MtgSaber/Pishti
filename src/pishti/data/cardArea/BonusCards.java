package pishti.data.cardArea;

import pishti.data.card.Card;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class BonusCards {
    private ArrayList<Card> cards;

    public BonusCards() {
        this.cards = new ArrayList<>();
    }

    public void setCards(Card card1, Card card2, Card card3) {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        for (Card card: cards) card.setFaceUp(false);
    }

    public ArrayList<Card> getCards() { return this.cards; }
}
