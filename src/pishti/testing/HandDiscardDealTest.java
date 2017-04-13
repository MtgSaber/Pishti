package pishti.testing;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.data.Rules;

/**
 * Author: Andrew Arnold (4/12/2017)
 *
 * WIP
 * used for testing gui design.
 */
public class HandDiscardDealTest extends Application {
    private Data gameData;

    public void start(Stage primaryStage) {
        gameData = new Data();

        BorderPane pnPrimary = new BorderPane();
        StackPane captured = new StackPane();
        HBox hand = new HBox(5);
        BorderPane deck = new BorderPane();
        BorderPane discard = new BorderPane();

        Button btDeal = new Button("Draw");
        Text capturedCount = new Text();
        Text deckCount = new Text();
        Text discardCount = new Text();
    }
}
