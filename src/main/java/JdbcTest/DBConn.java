package JdbcTest;

import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConn {

    private static final String url = "jdbc:mysql://localhost:3306/aaa";		//数据库地址
    private static final String username = "root";		//数据库用户名
    private static final String password = "root";		//数据库密码
    private static final String driver = "com.mysql.jdbc.Driver";		//mysql驱动
    private static final Connection conn = null;

    /**
     * 连接数据库
     * @return
     */
    public static Connection conn() {
        Connection conn = null;
        try {
            Class.forName(driver);  //加载数据库驱动
            try {
                conn = DriverManager.getConnection(url, username, password);  //连接数据库
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库链接
     * @return
     */
    public static void close() {
        if(conn != null) {
            try {
                conn.close();  //关闭数据库链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

