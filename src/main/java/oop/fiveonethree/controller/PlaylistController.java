package oop.fiveonethree.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import lombok.extern.log4j.Log4j2;
import oop.fiveonethree.utils.FileUtil;
import oop.fiveonethree.utils.PropertiesUtil;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
@Log4j2
public class PlaylistController implements Initializable {

    @FXML
    private ListView playList;

    private ObservableList playListFiles = FXCollections.observableArrayList();
    private ObjectProperty<Path> selectedMedia = new SimpleObjectProperty<>();
    private ObjectProperty<Path> deletedMedia = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playList.setOnMouseClicked((click) -> {
            if (click.getClickCount() == 2) {
                if (playList.getSelectionModel().getSelectedItem() != null) {
                    selectedMedia.setValue((Path) playList.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Files",
                            PropertiesUtil.readFormats()));
            List<Path> listOfFiles = new ArrayList<Path>();
            listOfFiles = FileUtil.convertFileToPath(chooser.showOpenMultipleDialog(((Button) event.getSource()).getScene().getWindow()));
            if (listOfFiles != null) {
                playListFiles.addAll(listOfFiles);
                playList.setItems(playListFiles);
            }
        } catch (Exception e) {
            log.debug("OK，已经关闭~");
        }

    }

    @FXML
    void delete(ActionEvent event) {
        try {
            if (playList.getSelectionModel().getSelectedItem() != null) {
                if(null!=playListFiles || !playListFiles.isEmpty()) {
                    deletedMedia.setValue((Path) playList.getSelectionModel().getSelectedItem());
                    playListFiles.remove(playList.getSelectionModel().getSelectedItem());
                    playList.setItems(playListFiles);
                }
            }
        } catch (Exception e) {
            log.debug("OK, OK, it's over");
        }
    }

    ObservableList listViewItems(){
        return playListFiles;
    }

    ObjectProperty<Path> selectedFile(){
        return selectedMedia;
    }

    ObjectProperty<Path> deletedFile() {
        return deletedMedia;
    }
}
