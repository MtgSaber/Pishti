package pishti;

import javafx.application.Application;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.data.GameNodes;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class Main extends Application {
    private GameNodes gameNodes;
    private Data data;
    private Game game;

    public void start(Stage primaryStage) {
        data = new Data();
        gameNodes = new GameNodes();
        game = new Game(data, gameNodes);
        game.initialize();
        maintenanceCycle();
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
