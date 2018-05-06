package oop.fiveonethree.mapper;

import oop.fiveonethree.model.Media;

import java.util.List;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public interface PlayerListMapper {

    boolean addMedia(Media media);

    boolean deleteMediaById(Media media);

    boolean updateMediaById(Media media);

    List<Media> selectMedias();

    int getNextId();
}
