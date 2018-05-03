package oop.fiveonethree.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import oop.fiveonethree.utils.DateTimeUtil;
import oop.fiveonethree.utils.PropertiesUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private Label timeLabel;

    @FXML
    private ToggleButton volume;

    @FXML
    private Slider volumeSlider;

    // 此列表在变化时可以被 listener 监听
    private ObservableList mediaFiles = FXCollections.observableArrayList();
    // 有无暂停请求
    private boolean pauseRequest = false;
    // 视频时长
    private Duration duration;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
        if (!mediaFiles.contains(theFile)){
            mediaFiles.add(theFile);
            playMedia(filePath);
        } else {
            playMedia(filePath);
        }
    }

    @FXML
    void exitPlayer(ActionEvent event) {

    }

    private void playMedia(String filePath) {
        try {
            String encodedUrl = URLEncoder.encode(filePath, "UTF-8");
            // todo 空格编码？
            encodedUrl = encodedUrl.replace("+", "%20");
            encodedUrl = "file:/" + encodedUrl;

            // 找到该媒体文件的位置，并拉入程序
            Media media = new Media(encodedUrl);
            checkAndStopMedia();
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            // 此方法为 player 绑定一些监听器
            setupMediaPlayer(mediaPlayer);

            // 当准备好资源时，立即播放
            mediaPlayer.setAutoPlay(true);
            // 设置播放时的标题
            ((Stage)mediaView.getScene().getWindow()).setTitle(Paths.get(filePath).getFileName().toString());

            mediaPlayer.play();
            // 缩放时保持比例
            mediaView.setPreserveRatio(false);
            mediaView.autosize();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void checkAndStopMedia() {
        if (mediaView.getMediaPlayer() != null) {
            // todo stop action
            mediaView.setMediaPlayer(null);
        }
    }

    /**
     * 一旦打开了任何文件，都会调用此方法设置 MediaPlayer 的相关监听器
     * @param mediaPlayer
     */
    private void setupMediaPlayer(MediaPlayer mediaPlayer) {

        // 播放时间变化时的监听器
        mediaPlayer.currentTimeProperty().addListener((observable) ->
                updateValues(mediaPlayer)
        );

        // 正在播放时 开一个线程，用于检测是否有暂停请求，如果有，暂停 mediaPlayer
        mediaPlayer.setOnPlaying(() -> {
            if (pauseRequest) {
                mediaPlayer.pause();
                pauseRequest = false;
            } else {
                playAndPause.setId("pause");
            }
        });

        // 正在暂停时 开一个线程
        mediaPlayer.setOnPaused(() -> playAndPause.setId("play"));

        // 准备就绪时 media 已经准备好了，更新页面信息
        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            updateValues(mediaPlayer);
        });

        // 时间进度条每改变一点，去更新视频位置
        // 主要作用为 拖动进度条时，改变视频位置
        timeSlider.valueProperty().addListener((observable -> {
            if (timeSlider.isValueChanging()) {
                // slider 默认值为 0-100 故除100 得到百分比，乘duration则为拖动到的时间
                mediaPlayer.seek(duration.multiply(timeSlider.valueProperty().getValue() / 100.0));
            }
        }));

        // 拖动音量进度条
        volumeSlider.valueProperty().addListener((observable -> {
            if (volumeSlider.isValueChanging()) {
                Double nowVolume = volumeSlider.valueProperty().getValue();
                if (nowVolume > 0) volume.setSelected(false);
                else if (nowVolume == 0) volume.setSelected(true);

                // 设置当前播放的音量
                mediaPlayer.setVolume(nowVolume / 100.0);
            }
        }));
    }


    /**
     * 一旦播放时间更新，则去更新其他按键、slider等等的信息
     * @param mediaPlayer
     */
    private void updateValues(MediaPlayer mediaPlayer) {
        if (timeLabel != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(() -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                timeLabel.setText(DateTimeUtil.formatPlayTime(currentTime, duration));
                // 当无法获取总时长时，不允许拖动进度条
                timeSlider.setDisable(duration.isUnknown());

                // 进度条未被禁用（可获取总时长），并且用户没有拖动时间条
                // 即 视频正常播放。此时修改进度条
                if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                    timeSlider.valueProperty().setValue(currentTime.divide(duration.toMillis()).toMillis() * 100.0);
                }

                //
                if (!volumeSlider.isValueChanging()) {
                    volumeSlider.valueProperty().setValue((int) mediaPlayer.getVolume() * 100);
                }
            });
        }
    }

    public void addSubs(ActionEvent event) {
    }

    public void openPlaylist(ActionEvent event) {
    }

    public void playAction(ActionEvent event) {
    }

    public void stopAction(ActionEvent event) {
    }

    public void muteUnmute(ActionEvent event) {
    }
}
