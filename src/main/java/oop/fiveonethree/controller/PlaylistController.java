package oop.fiveonethree.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import lombok.extern.log4j.Log4j2;
import oop.fiveonethree.model.Media;
import oop.fiveonethree.service.PlayerListService;
import oop.fiveonethree.utils.FileUtil;
import oop.fiveonethree.utils.PropertiesUtil;

import java.io.File;
import java.nio.file.Path;
import java.util.List;


/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
@Log4j2
public class PlaylistController {

    private PlayerListService service = PlayerListService.getInstance();

    @FXML
    private ListView playList;

    private ObservableList playListFiles = FXCollections.observableArrayList();
    private ObjectProperty<Media> selectedMedia = new SimpleObjectProperty<>();
    private ObjectProperty<Media> deletedMedia = new SimpleObjectProperty<>();

    @FXML
    void add(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("媒体文件", PropertiesUtil.readFormats())
        );
        List<File> chooseFiles = chooser.showOpenMultipleDialog(((Button) event.getSource()).getScene().getWindow());
        List<Path> filesPath = FileUtil.convertFileToPath(chooseFiles);
        for (Path path : filesPath) {
            Media m = new Media();
            m.setUrl(path.toString());
            m.setName(path.getFileName().toString());
            service.addMedia(m);

            playListFiles.add(m);
        }
        playList.setItems(playListFiles);
    }

    @FXML
    void delete(ActionEvent event) {
        Object selected = playList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (playListFiles != null && !playListFiles.isEmpty()) {
                deletedMedia.setValue((Media) selected);
                playListFiles.remove(selected);
                service.deleteMedia((Media) selected);

                playList.setItems(playListFiles);
            }
        }
    }

    ObservableList listViewItems(){
        return playListFiles;
    }

    ObjectProperty<Media> selectedFile(){
        return selectedMedia;
    }

    ObjectProperty<Media> deletedFile() {
        return deletedMedia;
    }
}
