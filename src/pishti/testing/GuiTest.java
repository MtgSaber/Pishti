package pishti.testing;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import pishti.gui.GameNodes;

import java.util.Observable;

/**
 * Created by ktrocoll on 4/19/2017.
 */
public class GuiTest extends Application {
    public void start (Stage primaryStage) {
        GameNodes gameNodes = new GameNodes();
        BorderPane BPane = new BorderPane();

        //Declare all the boxes we will use
        //BOXES!
        //Main vbox to hole all the elements
        VBox main = new VBox(5);

        //HBox 1, AI's play region(aiReg), including the capture value, capture pile, and hand
        HBox aiReg = new HBox(5);
        VBox aiCapVal = new VBox(5);
        VBox aiCapPile = new VBox(5);
        VBox aiHand = new VBox(5);

        //HBox 2, main playing region (mainReg), including deck count, deck image, discard image, and discard infro which includes discard count and discard value
        HBox mainReg = new HBox(5);
        VBox decCount = new VBox(5);
        VBox decImg = new VBox(5);
        VBox disImg = new VBox(5);
        VBox disInfo = new VBox(5);
        HBox disCount = new HBox(5);
        HBox disVal = new HBox(5);

        //HBox 3, the player region (playReg), including the capture value, capture pile, and hand
        HBox playReg = new HBox(5);
        VBox playCapVal = new VBox(5);
        VBox playCapPile = new VBox(5);
        VBox playHand = new VBox(5);

        //Setting sizes
        aiReg.setPrefSize(480, 116);
        aiHand.setPrefSize(308, 106);



        //Adding all the VBoxes to their respective HBox
        //HBox1
        aiReg.getChildren().add(aiCapVal);
        aiReg.getChildren().add(aiCapPile);
        aiReg.getChildren().add(aiHand);

        //HBox 2
        mainReg.getChildren().add(decCount);
        mainReg.getChildren().add(decImg);
        mainReg.getChildren().add(disImg);
        //Adding the discard info to their HBox
        disInfo.getChildren().add(disCount);
        disInfo.getChildren().add(disVal);
        //Adding all of that to the main HBox
        mainReg.getChildren().add(disInfo);

        //HBox 3
        playReg.getChildren().add(playCapVal);
        playReg.getChildren().add(playCapPile);
        playReg.getChildren().add(playHand);

        //Adding all 3 HBoxes to the main VBox
        main.getChildren().add(aiReg);
        main.getChildren().add(mainReg);
        main.getChildren().add(playReg);

        //Add Pane to the stage and show the stage window
        primaryStage.setScene(new Scene(BPane));
        primaryStage.show();
    }

}
