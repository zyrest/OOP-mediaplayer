package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class LogTest {

    static Logger logger = LogManager.getLogger();

    @Test
    public void test() {
        logger.error("blabla -> {}", "332211");
    }
}
