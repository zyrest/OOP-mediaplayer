package oop.fiveonethree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.fiveonethree.controller.MediaController;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/fiveonethree/fxml/media-player.fxml"));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane, 650, 400);
        primaryStage.setScene(scene);

        MediaController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        launch(args);
    }
}
