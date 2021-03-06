package pishti.data;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;

/**
 * Author(s): Andrew Arnold, Kevin Tricolli, Zack Jenkins
 *
 * Holds all the nodes in the primary stage of the game application.
 * Serves as a resource wrapper for more organized upper-level code.
 */
public class GameNodes {
    private HBox handPlayer;
    private HBox handAI;
    private ImageView capturedPlayer;
    private ImageView capturedAI;
    private ImageView deck;
    private ImageView discard;

    private Text capturedPlayerCnt;
    private Text capturedAICnt;
    private Text capturedPlayerVal;
    private Text capturedAIVal;
    private Text deckCnt;
    private Text discardCnt;
    private Text discardVal;
    private Text prompt;

    public GameNodes() {
        handPlayer = new HBox(5);
        handAI = new HBox(5);
        capturedPlayer = new ImageView(new File("assets/card/b2fv.png").toURI().toString());
        capturedAI = new ImageView(new File("assets/card/b2fv.png").toURI().toString());
        deck = new ImageView(new File("assets/card/b2fv.png").toURI().toString());
        discard = new ImageView();

        capturedPlayerCnt = new Text("0");
        capturedAICnt = new Text("0");
        capturedPlayerVal = new Text("0");
        capturedAIVal = new Text("0");
        deckCnt = new Text("0");
        discardCnt = new Text("0");
        discardVal = new Text("0");
        prompt = new Text("Play a card by clicking on it!");
        prompt.setStroke(Color.BLUE);
    }

    public HBox getHandPlayer() { return handPlayer; }
    public HBox getHandAI() { return handAI; }
    public ImageView getCapturedPlayer() { return capturedPlayer; }
    public ImageView getCapturedAI() { return capturedAI; }
    public ImageView getDeck() { return deck; }
    public ImageView getDiscard() { return discard; }
    public Text getCapturedPlayerCnt() { return capturedPlayerCnt; }
    public Text getCapturedAICnt() { return capturedAICnt; }
    public Text getCapturedPlayerVal() { return capturedPlayerVal; }
    public Text getCapturedAIVal() { return capturedAIVal; }
    public Text getDeckCnt() { return deckCnt; }
    public Text getDiscardCnt() { return discardCnt; }
    public Text getDiscardVal() { return discardVal; }
    public Text getPrompt() { return prompt; }
}
