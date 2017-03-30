package pishti.gui.panes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import pishti.data.cardArea.CardArea;
import pishti.gui.images.CardImg;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/29/2017)
 */
public abstract class CardPane extends BorderPane {
    private ArrayList<CardImg> cardImgs;
    private CardArea data;

    public CardPane(Pane center, CardArea data) {
        super(center);
        this.data = data;
        cardImgs = new ArrayList<>();
    }

    protected ArrayList<CardImg> getCardImgs() { return cardImgs; }
    protected CardArea getSuperData() { return data; }

    @Override
    public ObservableList<Node> getChildren() {
        Pane center = (Pane) super.getCenter();
        return center.getChildren();
    }

    public abstract ArrayList<CardImg> getCards();
    public abstract CardArea getData();
}
