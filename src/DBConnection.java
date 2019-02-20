import java.sql.*;

public class DBConnection {
    public Connection con;
    public DBConnection(){
    try{
        //loading database driver for JDBC
    Class.forName("com.mysql.jdbc.Driver");
    
    //connecting java with database
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nibl","root","");
    }
    
    catch(Exception ex){
    System.out.println("Error: "+ex);
    }
    }
}
