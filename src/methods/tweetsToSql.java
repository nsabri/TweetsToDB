/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connection.MySqlConnection;

/**
 *
 * @author nSabri
 */
public class tweetsToSql {
    
    private static Connection connection;
    private static PreparedStatement preparedStmt = null;

    public void add( String Name,String Description,String Text) throws SQLException {
        String query = " insert into tweetsTable (UserName,UserDescription,UserText)"
                + " values (?, ?, ?) ";
        connection = MySqlConnection.connectToDB();
        preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, Name);
        preparedStmt.setString(2, Description);
        preparedStmt.setString(3, Text);
        preparedStmt.execute();
    }
    
}
