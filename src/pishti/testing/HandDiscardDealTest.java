package pishti.testing;

import javafx.application.Application;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.data.GameNodes;
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


}
