package JdbcTest;


import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Connection conn = DBConn.conn();
        String sql = "select * from test.test";
        String sql2= "insert into test(`name`) VALUES ('bb')";
        PreparedStatement pstmt;
        List<Sync> list = new ArrayList();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("============================");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            //pstmt.execute(sql2);
            System.out.println("============================");
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


