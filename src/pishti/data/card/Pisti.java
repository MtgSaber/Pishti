package pishti.data.card;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class Pisti {
    private Rank rank;
    private ArrayList<Card> pair;

    public Pisti(Rank rank, Card card1, Card card2) {
        this.rank = rank;
        this.pair = new ArrayList<>();
        this.pair.add(card1);
        this.pair.add(card2);
    }

    public Rank getRank() { return this.rank; }
    public ArrayList<Card> getPair() { return this.pair; }
}
