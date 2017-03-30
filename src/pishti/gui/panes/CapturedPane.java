package pishti.gui.panes;

import javafx.scene.layout.Pane;
import pishti.data.cardArea.Captured;
import pishti.gui.images.CardImg;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class CapturedPane extends CardPane {
    private Captured data;

    public CapturedPane(Captured data) {
        super(new Pane(), data);
        this.data = data;
    }

    public Captured getData() { return data;}
    public ArrayList<CardImg> getCards() { return super.getCardImgs(); }
}
