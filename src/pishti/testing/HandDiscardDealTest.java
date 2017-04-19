package pishti.testing;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pishti.Game;
import pishti.data.Data;
import pishti.data.GameNodes;

/**
 * Author: Andrew Arnold (4/12/2017)
 *
 * WIP
 * used for testing gui design.
 */
public class HandDiscardDealTest extends Application {
    private GameNodes gameNodes;
    private Data data;
    private Game game;

    public void start(Stage primaryStage) {
        data = new Data();
        gameNodes = new GameNodes();
        game = new Game(data, gameNodes);
        game.initialize();
        maintenanceCycle();

        VBox vbAIInfo = new VBox(5, gameNodes.getCapturedAIVal(), gameNodes.getCapturedAICnt());
        VBox vbPlayerInfo = new VBox(5, gameNodes.getCapturedPlayerVal(), gameNodes.getCapturedPlayerCnt());
        VBox vbDiscardInfo = new VBox(5, gameNodes.getDiscardVal(), gameNodes.getDiscardCnt());

        HBox hbAIPane = new HBox(5, vbAIInfo, gameNodes.getCapturedAI(), gameNodes.getHandAI());
        HBox hbPlayerPane = new HBox(5, vbAIInfo, gameNodes.getCapturedPlayer(), gameNodes.getHandPlayer());
        HBox hbCenter;
    }



    private void maintenanceCycle() {
        gameNodes.getDeckCnt().setText(""+data.getDeck().size());
        gameNodes.getDiscardCnt().setText(""+data.getDiscard().size());
        gameNodes.getCapturedAICnt().setText(""+data.getCapturedAI().size());
        gameNodes.getCapturedPlayerCnt().setText(""+data.getCapturedUser());
        gameNodes.getDiscardVal().setText(""+game.getScore(data.getDiscard(), false, false));
        gameNodes.getCapturedPlayerVal().setText(""+game.getScore(data.getCapturedUser(), true, false));
        gameNodes.getCapturedAIVal().setText(""+game.getScore(data.getCapturedAI(), false, false));

        data.switchTurn();

        if (data.isUserTurn())
            gameNodes.getPrompt().setText("Play a card");

        if (data.getHandUser().size() < 4) {
            game.deal(true);
        }

        if (data.getHandAI().size() < 4) {
            game.deal(false);
        }
    }
}
