/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author nSabri
 */
public class MySqlConnection {
        static Connection conn = null;
    static PreparedStatement preparedStmt = null;
    final static String DBdriver = "com.mysql.jdbc.Driver";
    final static String DBurl = "jdbc:mysql://localhost:3306/tweetsDB";
    final static String DBuser = "root";
    final static String DBpassword = "root";

    public static Connection connectToDB() {
        try {
            if (conn == null) {
                Class.forName(DBdriver);
                return conn = DriverManager.getConnection(DBurl, DBuser, DBpassword);
            }
            return conn;
        } catch (Exception e) {
            return null;
        }
    }
}

