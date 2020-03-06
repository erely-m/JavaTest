package com.erely.mybatis;

import java.sql.*;

public class SQLTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC", "root",
                "root");
        String sql = "select * from Test WHERE 1 = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.absolute(2);
        ResultSetMetaData rsm =  rs.getMetaData();
        System.out.println(rsm.getColumnCount());
        System.out.println(rsm.getColumnClassName(1));

    }
}
