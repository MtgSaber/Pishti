package pishti.gui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Author: Andrew Arnold (4/13/2017)
 *
 * Holds all the nodes in the primary stage of the game application.
 * Serves as a resource wrapper for more organized upper-level code.
 */
public class GameNodes {
    private BorderPane pnPrimary;
    private HBox handPlayer;
    private HBox handAI;
    private VBox capturedPlayer;
    private VBox capturedAI;
    private VBox deck;
    private VBox discard;

    private Button btDeal;
    private Text capturedPlayerCnt;
    private Text capturedAICnt;
    private Text capturedPlayerVal;
    private Text capturedAIVal;
    private Text deckCnt;
    private Text discardCnt;
    private Text discardVal;

    public GameNodes() {
        pnPrimary = new BorderPane();
        capturedPlayer = new VBox(5);
        capturedAI = new VBox(5);
        handPlayer = new HBox(5);
        handAI = new HBox(5);
        deck = new VBox(5);
        discard = new VBox(5);

        btDeal = new Button("Draw");
        capturedPlayerCnt = new Text("0");
        capturedAICnt = new Text("0");
        capturedPlayerVal = new Text("0");
        capturedAIVal = new Text("0");
        deckCnt = new Text("48");
        discardCnt = new Text("4");
        discardVal = new Text("0");
    }

    public BorderPane getPnPrimary() {return pnPrimary;}
    public HBox getHandPlayer() {return handPlayer;}
    public HBox getHandAI() {return handAI;}
    public VBox getCapturedPlayer() {return capturedPlayer;}
    public VBox getCapturedAI() {return capturedAI;}
    public VBox getDeck() {return deck;}
    public VBox getDiscard() {return discard;}
    public Button getBtDeal() {return btDeal;}
    public Text getCapturedPlayerCnt() {return capturedPlayerCnt;}
    public Text getCapturedAICnt() {return capturedAICnt;}
    public Text getCapturedPlayerVal() {return capturedPlayerVal;}
    public Text getCapturedAIVal() {return capturedAIVal;}
    public Text getDeckCnt() {return deckCnt;}
    public Text getDiscardCnt() {return discardCnt;}
    public Text getDiscardVal() {return discardVal;}
}
