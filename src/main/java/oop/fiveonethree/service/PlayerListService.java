package oop.fiveonethree.service;

import lombok.extern.log4j.Log4j2;
import oop.fiveonethree.mapper.PlayerListMapper;
import oop.fiveonethree.mapper.PlayerListRepository;
import oop.fiveonethree.model.Media;

import java.util.List;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
@Log4j2
public class PlayerListService {

    private PlayerListMapper mapper = PlayerListRepository.getInstance();

    // 实现单例模式，并保证并发锁
    private PlayerListService(){}
    private static class Singleton {
        private static final PlayerListService INSTANCE = new PlayerListService();
    }
    public static PlayerListService getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * 插入一个media到数据库中，并返回给定的id
     * @param media
     */
    public void addMedia(Media media) {
        int nextId = mapper.getNextId();
        media.setId(nextId);
        boolean inserted = mapper.addMedia(media);
        if (!inserted) log.debug("插入失败！-> {}", media);
    }

    public void deleteMedia(Media media) {
        mapper.deleteMediaById(media);
    }

    public void updateMedia(Media media) {
        mapper.updateMediaById(media);
    }

    public List<Media> getAllMedias() {
        return mapper.selectMedias();
    }
}
