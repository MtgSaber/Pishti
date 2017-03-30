package pishti.gui.panes;

import javafx.scene.layout.StackPane;
import pishti.data.cardArea.Hand;
import pishti.gui.images.CardImg;

import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/29/2017)
 */
public class HandPane extends Hand {
    private StackPane pane;
    private ArrayList<CardImg> cardImgs;

    public HandPane(Hand hand) {
        super(hand.getOwner());
        super.getCardsHeld().clear();
        super.getCardsHeld().addAll(hand.getCardsHeld());
        cardImgs = new ArrayList<>();
        this.pane = new StackPane();
        pane.setOnDragEntered(event -> {
            if (event.getSource() instanceof CardImg) {
                CardImg cardImg = (CardImg) event.getSource();
                cardImgs.add(cardImg);
                pane.getChildren().add(cardImg.getImage());
            }
        });
    }

    public StackPane getPane() { return pane; }
    public ArrayList<CardImg> getCardImgs() { return cardImgs; }
}
