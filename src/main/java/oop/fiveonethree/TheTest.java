package oop.fiveonethree;

import oop.fiveonethree.mapper.PlayerListMapper;
import oop.fiveonethree.mapper.PlayerListRepository;
import oop.fiveonethree.model.Media;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class TheTest {
    public static void main(String[] args) {
        PlayerListMapper p = new PlayerListRepository();
        Media m = p.selectMedias().get(1);
        m.setName("yoyoyo");
        p.deleteMediaById(m);

        System.out.println(p.selectMedias());
    }
}
