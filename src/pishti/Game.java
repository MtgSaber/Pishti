package pishti;

import pishti.data.Data;
import pishti.data.card.Card;
import pishti.data.card.Rank;
import pishti.data.card.Suit;
import pishti.gui.GameNodes;
import pishti.gui.images.CardImg;

/**
 * Author: Andrew Arnold (4/17/2017)
 *
 * A Place for all the game methods.
 */
public class Game {
    public static final int HAND_LIMIT = 4;

    private Data data;
    private GameNodes gameNodes;

    public Game() {
        data = new Data();
        gameNodes = new GameNodes();
    }

    public Data getdata() { return data; }
    public GameNodes getGameNodes() { return gameNodes; }

    public int getScore(boolean player, boolean finalScore) {
        int score = 0;
        for (Card card: player? data.getCapturedUser(): data.getCapturedAI()) {
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
    public void deal(boolean toPlayer) {
        // check if deck is empty and whether the hand is below HAND_LIMIT.
        if (data.getDeck().size()>0
                && ((toPlayer)? data.getHandUser().size(): data.getHandAI().size())<HAND_LIMIT) {

            Card cardDealt = data.getDeck().remove(data.getDeck().size()-1);
            if (toPlayer) {
                cardDealt.setFaceUp(true);
                data.getHandUser().add(cardDealt);
                gameNodes.getHandPlayer().getChildren().add(new CardImg(cardDealt));
            } else {
                cardDealt.setFaceUp(false);
                data.getHandAI().add(cardDealt);
                gameNodes.getHandAI().getChildren().add(new CardImg(cardDealt));
            }
        }
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
    }
}
