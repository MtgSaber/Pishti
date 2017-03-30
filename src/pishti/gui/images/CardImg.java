package pishti.gui.images;

import javafx.scene.image.ImageView;
import pishti.data.card.Card;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class CardImg extends ImageView {
    private Card representee;

    public CardImg(Card card) {
        super("./assets/card/" + card.getNumber() + ".png");
        representee = card;
    }

    public Card getCard() { return representee; }
}
