package pishti.gui.images;

import javafx.scene.image.ImageView;
import pishti.data.card.Card;

import java.io.File;

/**
 * Author: Andrew Arnold (3/28/2017)
 *
 * wraps a Card in an image for easier upper-level coding.
 */
public class CardImg extends ImageView {
    private Card representee;

    public CardImg(Card card) {
        super(new File("assets/card/" + card.getNumber() + ".png")
                .toURI().toString());
        representee = card;
    }

    public Card getCard() { return representee; }
}
