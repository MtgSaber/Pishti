package pishti.testing;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pishti.data.card.Card;
import pishti.gui.images.CardImg;

import java.io.File;

/**
 * Author: Andrew Arnold (3/29/2017)
 */
public class CardDragging extends Application {
    public void start(Stage primaryStage) {
        VBox pane = new VBox();
        Pane cardPaneA = new Pane(), cardPaneB = new Pane();
        CardImg card = new CardImg(new Card(15));

        cardPaneA.getChildren().add(card);
        pane.getChildren().add(cardPaneA);
        pane.getChildren().add(cardPaneB);

        cardPaneA.setPrefWidth(500);
        cardPaneB.setPrefWidth(500);

        cardPaneA.setPrefHeight(250);
        cardPaneB.setPrefHeight(250);

        pane.setPrefHeight(525);

        card.setOnMouseDragged(event -> {
            card.setX(event.getX());
            card.setY(event.getY());
            primaryStage.show();
        });

        pane.setOnDragDetected(event -> {
            if (cardPaneB.contains(getLocalPoint(cardPaneB, event)))
                System.out.println("success\t\t" + event.getSource().toString());
        });

        cardPaneB.addEventHandler(DragEvent.ANY, event -> {
            System.out.println(event.getSource());
        });

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    private Point2D getLocalPoint(Pane pane, MouseEvent event) {
        return pane.sceneToLocal(event.getSceneX(), event.getSceneY());
    }

    public static void main(String[] args) { launch(args); }
}
