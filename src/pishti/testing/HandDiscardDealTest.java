package pishti.testing;

import javafx.application.Application;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.data.card.Card;
import pishti.gui.GameNodes;
import pishti.gui.images.CardImg;

/**
 * Author: Andrew Arnold (4/12/2017)
 *
 * WIP
 * used for testing gui design.
 */
public class HandDiscardDealTest extends Application {
    private Data gameData;
    private GameNodes gameNodes;

    public void start(Stage primaryStage) {
        gameData = new Data();
        gameNodes = new GameNodes();
    }

    private void refreshCycle() {
        gameNodes.getDeckCnt().setText(""+gameData.getDeck().size());
    }

    private void deal(boolean toPlayer) {
        if (gameData.getDeck().size()>0 && ((toPlayer)? gameData.getHandUser().size(): gameData.getHandAI().size())>0) {
            Card cardDealt = gameData.getDeck().remove(0);
            if (toPlayer) {
                gameData.getHandUser().add(cardDealt);
                gameNodes.getHandPlayer().getChildren().add(new CardImg(cardDealt));
            } else {
                gameData.getHandAI().add(cardDealt);
            }
        }
    }
}
