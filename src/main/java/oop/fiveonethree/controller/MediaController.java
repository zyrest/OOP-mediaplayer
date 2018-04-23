package oop.fiveonethree.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import oop.fiveonethree.utils.PropertiesUtil;

import java.nio.file.Path;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class MediaController {



    /**
     * top
     */
    @FXML
    private MenuBar menuBar;

    /**
     * centre
     */
    @FXML
    private MediaView mediaView;

    /**
     * bottom
     */
    @FXML
    private HBox mediaControl;

    @FXML
    private Button playAndPause;

    @FXML
    private Button stop;

    @FXML
    private Slider timeSlider;

    @FXML
    private Label currentTime;

    @FXML
    private ToggleButton volume;

    @FXML
    private Slider volumeSlider;

    /**
     * top action
     */
    @FXML
    void openFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files", PropertiesUtil.readFormats())
        );
        Window theWindow = ((MenuItem) event.getSource()).getParentPopup().getScene().getWindow();
        Path theFile = chooser.showOpenDialog(theWindow).toPath();
        String filePath = theFile.toString();
    }
}
