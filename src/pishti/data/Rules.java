package pishti.data;

import pishti.data.card.Card;
import pishti.data.card.Pisti;

/**
 * Author: Andrew Arnold (4/10/2017)
 */
public class Rules {
    public static final int HAND_LIMIT = 4;




    // returns true if winner is Player, false if AI.
    public static boolean doesPlayerWin(Data gameData) {
        return (getScore(gameData, true) > getScore(gameData, false));
    }

    // returns the score of either the player or the AI.
    private static int getScore(Data gameData, boolean player) {
        int score = 0;
        for (Object captured: player?
                gameData.getCapturedUser(): gameData.getCapturedAI()) {
            if (captured instanceof Card) {
                // TODO: handle card scoring.
            } else if (captured instanceof Pisti) {
                // TODO: handle pisti scoring.
            }
        }
        return score;
    }


    // TODO: design and implement rest of class.
}
