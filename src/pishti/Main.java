package pishti;

import javafx.application.Application;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.gui.GameNodes;

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

    }

    private void maintenanceCycle() {

    }
}
