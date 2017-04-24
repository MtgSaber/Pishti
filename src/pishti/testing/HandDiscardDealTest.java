package pishti.testing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pishti.Game;
import pishti.data.Data;
import pishti.data.GameNodes;
import pishti.data.card.Card;

import java.io.File;

/**
 * Author: Andrew Arnold (4/12/2017)
 *
 * WIP
 * used for testing gui design.
 */
public class HandDiscardDealTest extends Application {
    private GameNodes gameNodes;
    private Data data;
    private Game game;
    private Stage primaryStage;

    private class CardImg extends ImageView {
        private Card representee;

        public CardImg(Card card) {
            super((card.isFaceUp())?
                    new File("assets/card/" + (card.getNumber()) + ".png").toURI().toString():
                    new File("assets/card/b1fv.png").toURI().toString()
            );
            representee = card;
        }

        public Card getCard() { return representee; }
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        data = new Data();
        gameNodes = new GameNodes();
        game = new Game(data, gameNodes);

        VBox vbAIInfo = new VBox(5, gameNodes.getCapturedAIVal(), gameNodes.getCapturedAICnt());
        VBox vbPlayerInfo = new VBox(5, gameNodes.getCapturedPlayerVal(), gameNodes.getCapturedPlayerCnt());
        VBox vbDiscardInfo = new VBox(5, gameNodes.getDiscardVal(), gameNodes.getDiscardCnt());

        vbAIInfo.setCenterShape(true);
        vbPlayerInfo.setCenterShape(true);
        vbDiscardInfo.setCenterShape(true);

        HBox hbAIPane = new HBox(5, vbAIInfo, gameNodes.getCapturedAI(), gameNodes.getHandAI());
        HBox hbPlayerPane = new HBox(5, vbPlayerInfo, gameNodes.getCapturedPlayer(), gameNodes.getHandPlayer());
        HBox hbCenter = new HBox(5, gameNodes.getDeckCnt(), gameNodes.getDeck(), gameNodes.getDiscard(),
                vbDiscardInfo);

        hbAIPane.setCenterShape(true);
        hbCenter.setCenterShape(true);
        hbPlayerPane.setCenterShape(true);

        VBox main = new VBox(5, hbAIPane, hbCenter, gameNodes.getPrompt(), hbPlayerPane);
        main.setCenterShape(true);

        initialize();

        maintenanceCycle();

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

        maintenanceCycle();

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

        // deals initial hand to Player
        Card[] hand = new Card[] {
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

        // deals initial cards to discard
        hand = new Card[] {
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
    }

    private void maintenanceCycle() {
        if (data.getHandUser().size()==0 && data.getHandAI().size()==0 && data.getDeck().size()==0)
            endGame();
        else {
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
        int aiScore = game.getScore(data.getCapturedUser(), false, true);
        boolean win = playerScore >= aiScore;
        /*
        gameNodes.getReplay().setOnAction(event -> initialize());
        gameNodes.getQuit().setOnAction(event -> {
            try {
                stop();
            } catch (Exception ex) {
                System.err.println("Unexpected Error Upon Exit!");
                System.exit(1);
            }
        });

        Text txVictory = new Text("You Win!!!");
        txVictory.setStroke(Color.GREEN);
        Text txDefeat = new Text("YOU FAILED!");
        txDefeat.setStroke(Color.RED);

        VBox vbEndMenu = new VBox(10, win? txVictory: txDefeat, gameNodes.getReplay(), gameNodes.getQuit());
        primaryStage.getScene().setRoot(vbEndMenu);
        */

        gameNodes.getPrompt().setText(win? "You Win!": "YOU FAILED!");
        gameNodes.getPrompt().setStroke(win? Color.GREEN: Color.RED);
        primaryStage.show();
    }
}
