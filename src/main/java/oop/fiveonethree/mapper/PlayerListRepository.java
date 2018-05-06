package oop.fiveonethree.mapper;

import lombok.extern.log4j.Log4j2;
import oop.fiveonethree.model.Media;
import oop.fiveonethree.utils.jabc.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
@Log4j2
public class PlayerListRepository implements PlayerListMapper {

    private static class SingletonHolder {
        private static final PlayerListRepository INSTANCE = new PlayerListRepository();
    }

    private PlayerListRepository(){}

    public static PlayerListRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean addMedia(Media media) {
        boolean flag = false;
        Connection c = DatabaseUtil.getConnection();

        try {
            PreparedStatement stat = c.prepareStatement("insert into mediaList (id, url, name) values (?, ?, ?);");
            stat.setInt(1, media.getId());
            stat.setString(2, media.getUrl());
            stat.setString(3, media.getName());
            stat.execute();
            flag = true;
        } catch (SQLException e) {
            log.error("插入 mediaList 失败！", e);
        }

        return flag;
    }

    @Override
    public boolean deleteMediaById(Media media) {
        boolean flag = false;
        Connection c = DatabaseUtil.getConnection();

        try {
            PreparedStatement stat = c.prepareStatement("delete from mediaList where id=?");
            stat.setInt(1, media.getId());
            stat.execute();
            flag = true;
        } catch (SQLException e) {
            log.error("删除 mediaList 中的{}失败！", media.getId());
        }

        return flag;
    }

    @Override
    public boolean updateMediaById(Media media) {
        boolean flag = false;
        Connection c = DatabaseUtil.getConnection();

        try {
            PreparedStatement stat = c.prepareStatement("update mediaList set url=?, name=? where id=?");
            stat.setString(1, media.getUrl());
            stat.setString(2, media.getName());
            stat.setInt(3, media.getId());
            stat.execute();
            flag = true;
        } catch (SQLException e) {
            log.error("修改 mediaList 失败！", e);
        }

        return flag;
    }

    @Override
    public List<Media> selectMedias() {
        List<Media> ans = new ArrayList<>();

        Connection c = DatabaseUtil.getConnection();
        try {
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery("select * from mediaList");
            while (rs.next()) {
                Media m = new Media();
                m.setId(rs.getInt(1));
                m.setUrl(rs.getString(2));
                m.setName(rs.getString(3));
                ans.add(m);
            }
        } catch (SQLException e) {
            log.error("查询mediaList时出现错误！",e);
        }

        return ans;
    }

    public int getNextId() {
        int ans = 0;

        Connection c = DatabaseUtil.getConnection();
        try {
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery("select max(id) from mediaList");
            while (rs.next()) ans = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ans+1;
    }
}
