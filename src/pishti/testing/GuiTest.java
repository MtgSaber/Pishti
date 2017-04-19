package pishti.testing;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import pishti.gui.GameNodes;

/**
 * Created by ktrocoll on 4/19/2017.
 */
public class GuiTest extends Application {
    public void start (Stage primaryStage) {
        GameNodes gameNodes = new GameNodes();
        Pane main = new Pane();

        //Add elements to Pane
        main.getChildren().add(gameNodes.getPnPrimary());



        //Add Pane to the stage and show the stage window
        primaryStage.setScene(new Scene(main));
        primaryStage.show();
    }
}
