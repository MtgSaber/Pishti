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
        BorderPane BPane = new BorderPane();

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

        //Adding the Sub-VBoxes to the main HBox
        aiReg.getChildren().add(aiCapInfo);
        mainReg.getChildren().add(disInfo);
        playReg.getChildren().add(playCapInfo);

        //Adding all 3 HBoxes to the main VBox
        main.getChildren().add(aiReg);
        main.getChildren().add(mainReg);
        main.getChildren().add(playReg);

        //Add Pane to the stage and show the stage window
        primaryStage.setScene(new Scene(BPane));
        primaryStage.show();
    }

}
