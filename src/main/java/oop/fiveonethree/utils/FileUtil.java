package oop.fiveonethree.utils;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class FileUtil {
    private static String audioPostfix[] = {"mp3", "wav"};
    private static String videoPostfix[] = {"mp4", "avi"};

    public static boolean isAudio(String fileName) {
        String type = getFilePostfix(fileName);
        for (String one : audioPostfix)
            if (one.equals(type)) return true;

        return false;
    }

    public static boolean isVideo(String fileName) {
        String type = getFilePostfix(fileName);
        for (String one : videoPostfix)
            if (one.equals(type)) return true;

        return false;
    }

    private static String getFilePostfix(String fileName) {
        if (fileName == null || fileName.length() == 0) return null;
        else return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
