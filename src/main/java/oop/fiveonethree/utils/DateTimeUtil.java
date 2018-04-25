package oop.fiveonethree.utils;


import javafx.util.Duration;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class DateTimeUtil {
    public static String formatPlayTime(Duration now, Duration total) {
        int nowSecT = (int) now.toSeconds();
        int talSecT = (int) total.toSeconds();

        int nowHrs = nowSecT / (60*60);
        int talHrs = talSecT / (60*60);

        int nowMin = (nowSecT / 60) - (nowHrs * 60);
        int talMin = (talSecT / 60) - (talHrs * 60);

        int nowSec = nowSecT - (nowHrs * 60 * 60) - (nowMin * 60);
        int talSec = talSecT - (talHrs * 60 * 60) - (talMin * 60);

        if (talHrs > 0)
            return String.format("%d:%02d:%02d/%d:%02d:%02d", nowHrs, nowMin, nowSec,
                    talHrs, talMin, talSec);
        else
            return String.format("%02d:%02d/%02d:%02d", nowMin, nowSec, talMin, talSec);
    }
}
