package pishti;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pishti.data.Data;
import pishti.data.GameNodes;
import pishti.data.card.Card;

import java.io.File;

/**
 * Author: Andrew Arnold (3/28/2017)
 */
public class Main extends Application {
    private GameNodes gameNodes;
    private Data data;
    private Game game;
    private class CardImg extends ImageView {
        private Card representee;

        public CardImg(Card card) {
            super((card.isFaceUp())?
                    new File("assets/card/" + card.getNumber() + ".png").toURI().toString():
                    new File("assets/card/b1fv.png").toURI().toString()
            );
            representee = card;
        }

        public Card getCard() { return representee; }
    }

    public void start(Stage primaryStage) {
        data = new Data();
        gameNodes = new GameNodes();
        game = new Game(data, gameNodes);
        game.initialize();
        maintenanceCycle();
    }

    private void cardAction(Card card) {
        boolean captured = game.playCard(card);
        gameNodes.getHandPlayer().getChildren().remove(data.getHandUser().indexOf(card));
        if (captured)
            game.capture(true);

        maintenanceCycle();

        Card aiCard = game.getCard();
        gameNodes.getHandAI().getChildren().remove(0);
        captured = game.playCard(aiCard);
        if (captured)
            game.capture(false);

        maintenanceCycle();
    }

    private void initialize() {
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
            gameNodes.getHandPlayer().getChildren().add(new CardImg(card));
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
        gameNodes.getDeckCnt().setText(""+data.getDeck().size());
        gameNodes.getDiscardCnt().setText(""+data.getDiscard().size());
        gameNodes.getCapturedAICnt().setText(""+data.getCapturedAI().size());
        gameNodes.getCapturedPlayerCnt().setText(""+data.getCapturedUser().size());
        gameNodes.getDiscardVal().setText(""+game.getScore(data.getDiscard(), false, false));
        gameNodes.getCapturedPlayerVal().setText(""+game.getScore(data.getCapturedUser(), true, false));
        gameNodes.getCapturedAIVal().setText(""+game.getScore(data.getCapturedAI(), false, false));

        data.switchTurn();

        if (data.isUserTurn())
            gameNodes.getPrompt().setText("Play a card");

        if (data.getHandUser().size() < 4) {
            Card cardDealt = game.draw();
            cardDealt.setFaceUp(true);
            gameNodes.getHandPlayer().getChildren().add(new CardImg(cardDealt));
        }

        if (data.getHandAI().size() < 4) {
            Card cardDealt = game.draw();
            cardDealt.setFaceUp(false);
            gameNodes.getHandAI().getChildren().add(new CardImg(cardDealt));
        }
    }
}
