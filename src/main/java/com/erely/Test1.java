package com.erely;

import java.sql.*;

public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        long time = System.currentTimeMillis() - 10 * 24 * 60 * 60 * 1000;
        String url = "";
        String sql = "select user_name,count(user_name) as us from User t where t.login_timestamp > " + time + " group by user_name order by us";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, "user", "pass");
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        int count = 0;
        while (rs.next()) {
            if (count == 9) break;
            String user_name = rs.getString(0);
            int loginCount = rs.getInt(1);
            System.out.println(user_name + " " + loginCount);
            count++;
        }
        rs.close();
        conn.close();
    }

}
