package pishti.gui.panes;

import javafx.scene.layout.Pane;
import pishti.data.cardArea.Hand;
import pishti.data.player.AI;
import pishti.gui.images.CardImg;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class AIPane extends CardPane {
    private AI data;

    public AIPane(AI data) {
        super(new Pane(), data.getHand());
        this.data = data;
    }

    public AI getAI() { return data; }
    public Hand getData() { return data.getHand(); }
    public ArrayList<CardImg> getCards() { return super.getCardImgs(); }
}
