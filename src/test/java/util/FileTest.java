package util;

import oop.fiveonethree.utils.FileUtil;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class FileTest {

    public static void main(String[] args) {
        String file = "gun.sds.ds.test.mp3";

        System.out.println(FileUtil.isAudio(file));
    }
}
