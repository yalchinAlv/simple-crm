package database;

import model.Lead;
import model.VirtualServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeadDB {

    // JDBC driver name and database URL
    private static final String DATABASE_URL = "jdbc:derby:E:\\olddesktop\\Projects\\database\\lib\\SimplifyConsole";
    private static final String USERNAME = "Console";
    private static final String ConnPASSWORD = "simple";
    private Connection connection;

    public LeadDB() throws SQLException {
        //Create a connection
        connection = DriverManager.getConnection( DATABASE_URL, USERNAME, ConnPASSWORD);
    }

    // Returns true if values is inserted
    public boolean insertLead(Lead ld)  throws SQLException{

        String query = "INSERT INTO Leads ( name , sDate , eDate , mFee  , VS1 , VS2 , VS3 , VS4 , VS5 ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);


            stmt.setString(1, ld.getName());


            //stmt.setInt(2, vs.getVirtualCores());
            //stmt.setInt(3, vs.getVirtualRam());
           // stmt.setInt(4, vs.getDiskSpace());
            //stmt.setString(5,vs.getDiskType());


            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException se){

            return false;
        }

        return true;
    }


}
