package util;

import java.net.URLEncoder;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class FileTest {

    public static void main(String[] args) throws Exception {
        String file = "gun.sds.ds.test.mp6";

        String str = "/";
        System.out.println(URLEncoder.encode(str, "UTF-8"));

//        System.out.println(FileUtil.isAudio(file));
    }
}
