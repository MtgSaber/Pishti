package pishti.testing;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import pishti.data.GameNodes;

/**
 * Created by ktrocoll on 4/19/2017.
 */
public class GuiTest extends Application {
    public void start (Stage primaryStage) {
        GameNodes gameNodes = new GameNodes();

        //Declare all the boxes we will use
        //BOXES!
        //Main vbox to hole all the elements
        VBox main = new VBox(5);

        //HBox 1, AI's play region(aiReg), including the capture value, capture pile, and hand
        HBox aiReg = new HBox(5);
        VBox aiCapInfo = new VBox(5);

        //HBox 2, main playing region (mainReg), including deck count, deck image, discard image, and discard infro which includes discard count and discard value
        HBox mainReg = new HBox(5);
        VBox disInfo = new VBox(5);

        //HBox 3, the player region (playReg), including the capture value, capture pile, and hand
        HBox playReg = new HBox(5);
        VBox playCapInfo = new VBox(5);

        //Adding elements
        //First HBox
        //Add elements to the nested VBox
        aiCapInfo.getChildren().add(gameNodes.getCapturedAICnt());
        aiCapInfo.getChildren().add(gameNodes.getCapturedAIVal());
        //Add elements to the main HBox
        aiReg.getChildren().add(aiCapInfo);
        aiReg.getChildren().add(gameNodes.getCapturedAI());
        aiReg.getChildren().add(gameNodes.getHandAI());

        //Second HBox
        //Add elements to the nested VBox
        disInfo.getChildren().add(gameNodes.getDiscardCnt());
        disInfo.getChildren().add(gameNodes.getDiscardVal());
        //Add to the main HBox
        mainReg.getChildren().add(gameNodes.getDeckCnt());
        mainReg.getChildren().add(gameNodes.getDeck());
        mainReg.getChildren().add(gameNodes.getDiscard());
        mainReg.getChildren().add(disInfo);

        //Third HBox
        //Add elements to the nested VBox
        playCapInfo.getChildren().add(gameNodes.getCapturedPlayerCnt());
        playCapInfo.getChildren().add(gameNodes.getCapturedPlayerVal());
        //Add to the main HBox
        playReg.getChildren().add(playCapInfo);
        playReg.getChildren().add(gameNodes.getCapturedPlayer());
        playReg.getChildren().add(gameNodes.getHandPlayer());

        //Adding all 3 HBoxes to the main VBox
        main.getChildren().add(aiReg);
        main.getChildren().add(mainReg);
        main.getChildren().add(playReg);

        //Set size
        //Set the main HBox's to be all equal
        aiReg.setMinHeight(240);
        aiReg.setMinWidth(1280);
        aiCapInfo.setMinWidth(160);
        mainReg.setMinHeight(240);
        mainReg.setMinWidth(1280);
        playReg.setMinHeight(240);
        playReg.setMinWidth(1280);
        playCapInfo.setMinWidth(160);

        //Add Pane to the stage and show the stage window, sets the title and size
        primaryStage.setTitle("Pishti GUI TEST");
        primaryStage.setResizable(false);
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);
        primaryStage.setScene(new Scene(main));
        primaryStage.show();
    }

}
