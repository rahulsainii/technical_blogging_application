/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;
import java.sql.*;
/**
 *
 * @author RAHUL
 */
public class ConnectionProvider {
    private static Connection con;
    public static Connection getConnection()
    {
        try
        {
            if(con==null)
            {    
              //load driver class
              Class.forName("com.mysql.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mywebapplication","root","admin");
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return con;
    }
    
    
}
