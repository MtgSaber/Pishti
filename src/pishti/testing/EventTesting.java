package pishti.testing;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import pishti.gui.images.CardImg;

/**
 * Author: Andrew Arnold (3/30/2017)
 */
public class EventTesting extends Event {
    private CardImg card;
    private MouseEvent mouseEvent;

    public EventTesting(CardImg card, MouseEvent mouseEvent) {
        super(new EventType<EventTesting>());
        this.card = card;
        this.mouseEvent = mouseEvent;
    }

    public CardImg getCard() { return card; }
    public MouseEvent getMouseEvent() { return mouseEvent; }
}
