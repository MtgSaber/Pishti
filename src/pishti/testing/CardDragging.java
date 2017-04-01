package pishti.testing;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pishti.data.card.Card;
import pishti.gui.images.CardImg;

import java.io.File;
import java.util.ArrayList;

/**
 * Author: Andrew Arnold (3/29/2017)
 *
 * this is for testing other classes in the project.
 * currently it is being used to test the functionality of gui.images.CardImg.class and data.card.Card.class.
 */
public class CardDragging extends Application {
    private ArrayList<CardImg> deck, hand;
    private GridPane deckPane;
    private HBox handPane;
    private ImageView cardBack;
    private Text cardCount;

    public void start(Stage primaryStage) {
        VBox pane = new VBox(25);
        cardCount = new Text("52");
        handPane = new HBox(5);
        cardBack = new ImageView(new File("assets/card/b2fv.png").toURI().toString());
        deckPane = new GridPane();
        deckPane.add(cardBack, 1, 1);
        deckPane.add(cardCount, 2, 1);

        handPane.setMinSize(750, 250);
        deckPane.setMinSize(750, 250);

        hand = new ArrayList<>();
        deck = new ArrayList<>();
        for (int i=1; i<=52; i++)
            deck.add(new CardImg(new Card(i)));

        deckPane.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) dealCard(primaryStage);
        });

        pane.getChildren().add(deckPane);
        pane.getChildren().add(handPane);

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    private void dealCard(Stage stage) {
        if (deck.size() > 0) {
            CardImg dealtCard = deck.remove(deck.size()-1);
            System.out.println(dealtCard.getCard().getRank().getName()
                    + "\t\t" + dealtCard.getCard().getSuit().getName());
            hand.add(dealtCard);
            handPane.getChildren().add(dealtCard);

            dealtCard.setOnMousePressed(event -> {
                if (event.isSecondaryButtonDown()) returnCard(stage, dealtCard);
            });
        }
        if (deck.size() == 0)
            deckPane.getChildren().remove(cardBack);
        cardCount.setText("" + deck.size());
        stage.show();
    }

    private void returnCard(Stage stage, CardImg cardImg) {
        System.out.println(cardImg.getCard().getRank().getName() + "\t\t" + cardImg.getCard().getSuit().getName());
        if (deck.size() == 0)
            deckPane.add(cardBack, 1, 1);
        hand.remove(cardImg);
        handPane.getChildren().remove(cardImg);
        deck.add(cardImg);
        cardCount.setText("" + deck.size());
        stage.show();
    }

    private Point2D getLocalPoint(Pane pane, MouseEvent event) {
        return pane.sceneToLocal(event.getSceneX(), event.getSceneY());
    }

    public static void main(String[] args) { launch(args); }
}
