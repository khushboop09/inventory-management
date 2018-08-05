package projectwork;

import java.sql.Connection;
import java.sql.DriverManager;

public class database 
{

    public static Connection getConnection() 
    {
        Connection con =null;
        try 
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","root");
           
            
        }
        catch (Exception ex) 
        {
            
        }
            return con;
    }
    
    
}
