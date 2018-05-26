package oop.fiveonethree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import oop.fiveonethree.controller.MediaController;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
@Log4j2
public class HelloWorld extends Application {

    /**
     * 控制画面与播放器同等拉伸
     * @param controller
     * @param scene
     */
    private void bindSize(MediaController controller, Scene scene){
        controller.timeSliderWidth().bind(scene.widthProperty().subtract(300));
        controller.mediaViewWidth().bind(scene.widthProperty());
        controller.mediaViewHeight().bind(scene.heightProperty().subtract(70));
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/fiveonethree/fxml/media-player.fxml"));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane, 650, 400);
        primaryStage.setScene(scene);
        MediaController controller = loader.getController();

        FXMLLoader playListLoader = new FXMLLoader(getClass().getResource("/oop/fiveonethree/fxml/playlist.fxml"));
        playListLoader.load();
        controller.invokePlayListController(playListLoader.getController());
        controller.invokePlayListRoot(playListLoader.getRoot());

        controller.applyDragDrop(scene);
        controller.setStage(primaryStage);
        bindSize(controller, scene);
        primaryStage.show();


    }

    public static void main(String[] args) throws Exception {
        log.info("Hello OOP!");
        launch(args);
    }
}
