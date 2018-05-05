package util;

import oop.fiveonethree.utils.jabc.DatabaseUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
public class JdbcTest {

    @Test
    public void testJdbc() throws SQLException {
        Connection c = DatabaseUtil.getConnection();
        System.out.println(c.getSchema());

        Statement stat = c.createStatement();
        ResultSet set = stat.executeQuery("select * from mediaList");
        while (set.next()) {
            System.out.println(set.toString());
        }
    }

    @Test
    public void testxx() throws Exception {
    }
}
