package oop.fiveonethree.utils.jabc;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ZhouYing.
 * www.zhouying.xyz
 */
@Log4j2
public class DatabaseUtil {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            log.error("找不到 JDBC 类！严重错误！即将退出程序！错误信息 -> {}", e.getMessage());
            System.exit(0);
        }
    }

    public static Connection getConnection() {
        Connection c = null;
        try {
            // 注意要加 :resource: 使用相对路径
            c = DriverManager.getConnection("jdbc:sqlite::resource:playlist.db");
        } catch (SQLException e) {
            log.error("无法获得 Connection！严重错误！即将退出程序！错误信息 -> {}", e.getMessage());
            System.exit(0);
        }
        return c;
    }
}
