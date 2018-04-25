package util;

import oop.fiveonethree.utils.PropertiesUtil;

import java.util.List;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class FileTest {

    public static void main(String[] args) throws Exception {
        List ls = PropertiesUtil.readPostfixs();
        System.out.println(ls);

        ls = PropertiesUtil.readAudioPostfixs();
        System.out.println(ls);

        ls = PropertiesUtil.readVideoPostfixs();
        System.out.println(ls);

        ls = PropertiesUtil.readFormats();
        System.out.println(ls);
//        System.out.println(FileUtil.isAudio(file));
    }
}
