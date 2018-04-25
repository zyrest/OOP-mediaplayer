package oop.fiveonethree.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class PropertiesUtil {
    private final static Class CLAZZ = PropertiesUtil.class;

    public static List<String> readFormats() {
        List<String> formats = new ArrayList<>();

        try {
            InputStream in = CLAZZ.getClassLoader().getResourceAsStream("config/media-type.properties");
            Properties pro = new Properties();
            pro.load(in);
            formats.addAll(Arrays.asList(pro.getProperty("audio").split(",")));
            formats.addAll(Arrays.asList(pro.getProperty("video").split(",")));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return formats;
    }

    public static List<String> readPostfixs() {
        List<String> postfixs = new ArrayList<>();

        try {
            InputStream in = CLAZZ.getClassLoader().getResourceAsStream("config/media-type.properties");
            Properties pro = new Properties();
            pro.load(in);

            String audioStr = pro.getProperty("audio").replace("*.", "");
            String videoStr = pro.getProperty("video").replace("*.", "");
            String[] audios = audioStr.split(",");
            String[] videos = videoStr.split(",");
            postfixs.addAll(Arrays.asList(audios));
            postfixs.addAll(Arrays.asList(videos));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postfixs;
    }

    public static List<String> readAudioPostfixs() {
        List<String> postfixs = new ArrayList<>();

        try {
            InputStream in = CLAZZ.getClassLoader().getResourceAsStream("config/media-type.properties");
            Properties pro = new Properties();
            pro.load(in);

            String audioStr = pro.getProperty("audio").replace("*.", "");
            String[] audios = audioStr.split(",");
            postfixs.addAll(Arrays.asList(audios));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postfixs;
    }

    public static List<String> readVideoPostfixs() {
        List<String> postfixs = new ArrayList<>();

        try {
            InputStream in = CLAZZ.getClassLoader().getResourceAsStream("config/media-type.properties");
            Properties pro = new Properties();
            pro.load(in);

            String videoStr = pro.getProperty("video").replace("*.", "");
            String[] videos = videoStr.split(",");
            postfixs.addAll(Arrays.asList(videos));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postfixs;
    }
}
