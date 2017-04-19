package pishti;

import javafx.scene.image.ImageView;
import pishti.data.Data;
import pishti.data.card.Card;
import pishti.data.card.Rank;
import pishti.data.card.Suit;
import pishti.data.GameNodes;

import java.io.File;
import java.util.ArrayList;

/**
 * Author: Andrew Arnold (4/17/2017)
 *
 * A Place for all the game methods.
 */
public class Game {
    public static final int HAND_LIMIT = 4;

    private Data data;
    private GameNodes gameNodes;

    public Game(Data data, GameNodes gameNodes) {
        this.data = data;
        this.gameNodes = gameNodes;
    }

    public Data getdata() { return data; }
    public GameNodes getGameNodes() { return gameNodes; }

    public void initialize() {
        data.deckShuffle();

        for (int i=0; i<4; i++) {
            Card cardDealt = data.getDeck().remove(data.getDeck().size()-1);
            cardDealt.setFaceUp(false);
            data.getDiscard().add(cardDealt);
        }
    }

    public int getScore(ArrayList<Card> cards, boolean player, boolean finalScore) {
        int score = 0;
        for (Card card: cards) {
            if (!card.isFaceUp())
                card.setFaceUp(finalScore);
            if (card.isFaceUp()) {
                if (card.isPisti())
                    score += card.getRank()== Rank.JACK? 10: 5;
                else switch (card.getRank()) {
                    case KING: case QUEEN: case JACK: case ACE: score++; break;
                    case TWO: if (card.getSuit() == Suit.CLUBS) score += 2; break;
                    case TEN: score += card.getSuit()==Suit.DIAMONDS? 3: 1; break;
                }
            }
        }

        if (finalScore)
            if ((player && data.getCapturedUser().size()>data.getCapturedAI().size())
                    || (player && data.getCapturedAI().size() > data.getCapturedUser().size()))
                score += 3;

        return score;
    }

    /*
     * Deals the top card of the deck either toPlayer, else to AI.
     */
    public Card draw() {
        if (data.getDeck().size()>0)
            return data.getDeck().remove(data.getDeck().size()-1);
        return null;
    }

    /*
     * plays the specified card to the discard pile,
     * returns TRUE if stack is captured.
     */
    public boolean playCard(Card card) {
        card.setFaceUp(true);
        data.getDiscard().add(card);
        return (card.getRank() == data.getDiscard().get(data.getDiscard().size()-2).getRank());
    }

    /*
     * captures the discard pile into byPlayer? player's: AI's captured area,
     * and determines if pistis are created.
     */
    public void capture(boolean byPlayer) {
        if (data.getDiscard().size() == 2)
            data.getDiscard().get(data.getDiscard().size()-1).setPisti(true);
        data.getDiscard().get(data.getDiscard().size()-2).setPisti(true);

        if (byPlayer)
            for (int i=data.getDiscard().size()-1; i>=0; i--)
                data.getCapturedUser().add(data.getDiscard().remove(i));
        else
            for (int i=data.getDiscard().size()-1; i>=0; i--)
                data.getCapturedAI().add(data.getDiscard().remove(i));

        data.getDiscard().add(data.getDeck().remove(data.getDeck().size()-1));
    }

    /*
     * returns the AI's card choice.
     */
    public Card getCard() {
        int[] priorities = new int[data.getHandAI().size()];
        int max = 0;

        for (Card card: data.getHandAI()) {
            if (card.getRank() == data.getDiscard().get(data.getDiscard().size()-1).getRank()) {
                priorities[data.getHandAI().indexOf(card)] = 3;
                max = 3;
            } else if (getScore(data.getDiscard(), false, false) > 3 && card.getRank() == Rank.JACK) {
                priorities[data.getHandAI().indexOf(card)] = 2;
                max = max>2? max: 2;
            } else if (card.getRank() != Rank.JACK) {
                priorities[data.getHandAI().indexOf(card)] = 1;
                max = max>1? max: 1;
            }
        }

        for (Card card: data.getHandAI())
            if (priorities[data.getHandAI().indexOf(card)]==max)
                return card;

        return null;
    }
}
