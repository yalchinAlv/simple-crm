package database;
/*  Database for the user
*/

import java.awt.*;
import java.sql.*;
import javax.swing.table.AbstractTableModel;

public class UserDB {

    // JDBC driver name and database URL
    private static final String DATABASE_URL = "jdbc:derby:E:\\olddesktop\\Projects\\database\\lib\\SimplifyConsole";
    private static final String USERNAME = "Console";
    private static final String ConnPASSWORD = "simple";
    private Connection connection;

    public UserDB() throws SQLException{
        //Create a connection
        connection = DriverManager.getConnection( DATABASE_URL, USERNAME, ConnPASSWORD);
    }


    // Returns true if values is inserted
    public boolean insertUser( int id, String password, String category, String fullname )  throws SQLException{

        String query = "INSERT INTO Users (ID , password , category , fullname ) VALUES (?, ?, ?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, password);
            stmt.setString(3, category);
            stmt.setString(4, fullname);

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException se){
            return false;
        }

        return true;
    }

    //Return the user if user exists
    public String getUser( int id,String password) throws SQLException{

        String query =  "SELECT * FROM users WHERE (id = ?) and (password = ?)";
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        String result = "";

        try{
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.setString(2,password);

            rslt = stmt.executeQuery();

            while(rslt.next()){
                result = rslt.getString("fullname");
            }
            stmt.close();
        }
        catch (SQLException e){
            return "no";
        }

        return result;
    }
}
