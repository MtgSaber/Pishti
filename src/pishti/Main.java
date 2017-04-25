package pishti;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.data.GameNodes;
import pishti.data.card.Card;
import pishti.data.card.Rank;

import java.io.File;

/**
 * Author: Andrew Arnold (4/12/2017)
 */
public class Main extends Application {
    private GameNodes gameNodes;
    private Data data;
    private Game game;
    private Stage primaryStage;

    private class CardImg extends ImageView {
        private Card representee;

        CardImg(Card card) {
            super((card.isFaceUp())?
                    new File("assets/card/" + (card.getNumber()) + ".png").toURI().toString():
                    new File("assets/card/b1fv.png").toURI().toString()
            );
            representee = card;
        }

        Card getCard() { return representee; }
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        data = new Data();
        gameNodes = new GameNodes();
        game = new Game(data, gameNodes);

        primaryStage.setTitle("Pisti");
        primaryStage.setResizable(false);

        VBox vbAIInfo = new VBox(5, gameNodes.getCapturedAIVal(), gameNodes.getCapturedAICnt());
        VBox vbPlayerInfo = new VBox(5, gameNodes.getCapturedPlayerVal(), gameNodes.getCapturedPlayerCnt());
        VBox vbDiscardInfo = new VBox(5, gameNodes.getDiscardVal(), gameNodes.getDiscardCnt());
        VBox vbCaptureLabel = new VBox(5, gameNodes.getCapturedPlayer(), new Text("Captured\nCards"));

        HBox hbAIPane = new HBox(5, vbAIInfo, gameNodes.getCapturedAI(), gameNodes.getHandAI());
        HBox hbPlayerPane = new HBox(5, vbPlayerInfo, vbCaptureLabel, gameNodes.getHandPlayer());

        BorderPane fieldBox = new BorderPane();
        fieldBox.setLeft(new HBox(5, gameNodes.getDeckCnt(), gameNodes.getDeck()));
        fieldBox.setRight(new HBox(5, gameNodes.getDiscard(), vbDiscardInfo));

        BorderPane promptBox = new BorderPane(gameNodes.getPrompt());
        VBox main = new VBox(5, hbAIPane, fieldBox, promptBox, hbPlayerPane);
        main.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        initialize();

        maintenanceCycle();

        fieldBox.setPrefWidth(hbAIPane.getPrefWidth());
        promptBox.setPrefWidth(hbAIPane.getPrefWidth());

        primaryStage.setScene(new Scene(main));
        primaryStage.show();
    }

    private void cardAction(CardImg card) {
        System.out.println("Player played "+card.getCard().getRank().getName()
                +" of "+card.getCard().getSuit().getName()+".");
        boolean captured = game.playCard(card.getCard());
        data.getHandUser().remove(card.getCard());
        gameNodes.getHandPlayer().getChildren().remove(card);
        card.getCard().setFaceUp(true);
        gameNodes.getDiscard().setImage(card.getImage());
        if (captured) {
            game.capture(true);
            gameNodes.getCapturedPlayer().setImage(card.getImage());
        }

        data.firstTurnFinish();

        maintenanceCycle();

        new AudioClip(new File("assets/audio/deal_card.wav").toURI().toString()).play();

        Card aiCard = game.getCard();
        System.out.println("AI played "+aiCard.getRank().getName() +" of "+aiCard.getSuit().getName()+".");
        captured = game.playCard(aiCard);
        data.getHandAI().remove(aiCard);
        gameNodes.getHandAI().getChildren().remove(0);
        aiCard.setFaceUp(true);
        gameNodes.getDiscard().setImage(new CardImg(aiCard).getImage());
        if (captured) {
            game.capture(false);
            gameNodes.getCapturedAI().setImage(new CardImg(aiCard).getImage());
        }

        maintenanceCycle();
    }

    private void initialize() {
        data.deckShuffle();
        while (data.getDeck().get(data.getDeck().size()-5).getRank() == Rank.JACK)
            data.deckShuffle();

        // deals initial cards to discard
        Card[] hand = new Card[] {
                game.draw(),
                game.draw(),
                game.draw(),
                game.draw()
        };
        for (Card card: hand) {
            card.setFaceUp(false);
            data.getDiscard().add(card);
        }

        Card initialCard = game.draw();
        initialCard.setFaceUp(true);
        data.getDiscard().add(initialCard);
        gameNodes.getDiscard().setImage(new CardImg(initialCard).getImage());

        // deals initial hand to Player
        hand = new Card[] {
                game.draw(),
                game.draw(),
                game.draw(),
                game.draw()
        };
        for (Card card: hand) {
            card.setFaceUp(true);
            data.getHandUser().add(card);
            CardImg cardImg = new CardImg(card);
            cardImg.setOnMouseClicked(event -> cardAction(cardImg));
            gameNodes.getHandPlayer().getChildren().add(cardImg);
        }

        // deals initial hand to AI
        hand = new Card[] {
                game.draw(),
                game.draw(),
                game.draw(),
                game.draw()
        };
        for (Card card: hand) {
            card.setFaceUp(false);
            data.getHandAI().add(card);
            gameNodes.getHandAI().getChildren().add(new CardImg(card));
        }
    }

    private void maintenanceCycle() {
        if (data.getHandUser().size()==0 && data.getHandAI().size()==0 && data.getDeck().size()==0) {
            if (data.getDiscard().size()>0)
                gameNodes.getDiscard().setImage(new CardImg(data.getDiscard().get(data.getDiscard().size() - 1)).getImage());
            else
                gameNodes.getDiscard().setImage(new Image(new File("assets/card/b2fv.png").toURI().toString()));

            endGame();
        } else {
            System.out.println(data.toString());
            gameNodes.getDeckCnt().setText("Size:\t\t" + data.getDeck().size());
            gameNodes.getDiscardCnt().setText("Size:\t\t" + data.getDiscard().size());
            gameNodes.getCapturedAICnt().setText("Size:\t\t" + data.getCapturedAI().size());
            gameNodes.getCapturedPlayerCnt().setText("Size:\t\t" + data.getCapturedUser().size());
            gameNodes.getDiscardVal().setText("Points:\t" + game.getScore(data.getDiscard(), false, false));
            gameNodes.getCapturedPlayerVal().setText("Points:\t" + game.getScore(data.getCapturedUser(), true,
                    false));
            gameNodes.getCapturedAIVal().setText("Points:\t" + game.getScore(data.getCapturedAI(), false, false));

            if (data.getDiscard().size()>0)
                gameNodes.getDiscard().setImage(new CardImg(data.getDiscard().get(data.getDiscard().size() - 1)).getImage());
            else
                gameNodes.getDiscard().setImage(new Image(new File("assets/card/b2fv.png").toURI().toString()));

            if (data.getHandUser().size() == 0)
                for (int i = 1; i <= 4; i++) {
                    Card cardDealt = game.draw();
                    if (data.getDeck().size()>0) {
                        cardDealt.setFaceUp(true);
                        data.getHandUser().add(cardDealt);
                        CardImg cardImg1 = new CardImg(cardDealt);
                        cardImg1.setOnMouseClicked(event -> cardAction(cardImg1));
                        gameNodes.getHandPlayer().getChildren().add(cardImg1);
                    }

                    if (data.getDeck().size()>0) {
                        cardDealt = game.draw();
                        cardDealt.setFaceUp(false);
                        data.getHandAI().add(cardDealt);
                        CardImg cardImg2 = new CardImg(cardDealt);
                        cardImg2.setOnMouseClicked(event -> cardAction(cardImg2));
                        gameNodes.getHandAI().getChildren().add(cardImg2);
                    }
            }

            primaryStage.show();
        }
    }

    private void endGame() {
        int playerScore = game.getScore(data.getCapturedUser(), true, true);
        int aiScore = game.getScore(data.getCapturedAI(), false, true);
        boolean majority = data.getCapturedUser().size() >= data.getCapturedAI().size();
        playerScore += majority? 3: 0;
        aiScore += !majority? 3: 0;
        boolean win = playerScore >= aiScore;

        if (win) {
            gameNodes.getPrompt().setText("You Win!!!");
            gameNodes.getPrompt().setStroke(Color.ORANGE);
        } else {
            gameNodes.getPrompt().setText("YOU FAILED!");
            gameNodes.getPrompt().setStroke(Color.RED);
        }
        primaryStage.show();
    }
}
