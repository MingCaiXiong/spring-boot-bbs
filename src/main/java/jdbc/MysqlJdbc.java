package jdbc;

import java.sql.*;

public class MysqlJdbc {
    public static void main(String[] args) {
        String driverName = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://127.0.0.1:3306/scott";
        String sql = "SELECT * FROM emp";
        String username = "root";
        String password = "NewPassword";
        Connection conn = null;
        try {
            //1.加载JDBC驱动
            Class.forName(driverName);
            //2.建立连接
            conn = DriverManager.getConnection(URL, username, password);
            //3.创建命令（Statement)
            Statement ps = conn.createStatement();
            //4.处理结果
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("ename") + "," +
                        rs.getString("job") + "," +
                        rs.getFloat("sal"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5.关闭连接
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
