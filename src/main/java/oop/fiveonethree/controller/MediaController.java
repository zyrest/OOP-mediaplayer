package oop.fiveonethree.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaView;

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
}
