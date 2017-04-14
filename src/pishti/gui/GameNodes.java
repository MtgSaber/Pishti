package pishti.gui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Author: Andrew Arnold (4/13/2017)
 *
 * Holds all the nodes in the primary stage of the game application.
 * Serves as a resource wrapper for more organized upper-level code.
 */
public class GameNodes {
    private Stage primaryStage;
    private BorderPane pnPrimary;
    private StackPane captured;
    private HBox hand;
    private BorderPane deck;
    private BorderPane discard;
    private HBox center;

    private Button btDeal;
    private Text capturedCount;
    private Text deckCount;
    private Text discardCount;

    public GameNodes(Stage primaryStage) {
        pnPrimary = new BorderPane();
        captured = new StackPane();
        hand = new HBox(5);
        deck = new BorderPane();
        discard = new BorderPane();
        center = new HBox(10, deck, discard, captured);

        btDeal = new Button("Draw");
        capturedCount = new Text();
        deckCount = new Text();
        discardCount = new Text();
    }

    public Stage getPrimaryStage() { return primaryStage; }
    public BorderPane getPnPrimary() { return pnPrimary; }
    public StackPane getCaptured() { return captured; }
    public HBox getHand() { return hand; }
    public BorderPane getDeck() { return deck; }
    public BorderPane getDiscard() { return discard; }
    public HBox getCenter() { return center; }
    public Button getBtDeal() { return btDeal; }
    public Text getCapturedCount() { return capturedCount; }
    public Text getDeckCount() { return deckCount; }
    public Text getDiscardCount() { return discardCount; }
}
