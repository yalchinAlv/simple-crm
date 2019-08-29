package database;

import model.VirtualServer;

import java.sql.*;


public class VirtualServerDB {
    // JDBC driver name and database URL
    private static final String DATABASE_URL = "jdbc:derby:E:\\olddesktop\\Projects\\database\\lib\\SimplifyConsole";
    private static final String USERNAME = "Console";
    private static final String ConnPASSWORD = "simple";
    private Connection connection;

    public VirtualServerDB() throws SQLException {
        //Create a connection
        connection = DriverManager.getConnection( DATABASE_URL, USERNAME, ConnPASSWORD);
    }

    // Returns true if values is inserted
    public boolean insertVirtualServer(VirtualServer vs)  throws SQLException{

        String query = "INSERT INTO VirtualServers (ID , virtCores , virtRam , diskSpace , disktype) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        //boolean status = false;

        try {
            stmt = connection.prepareStatement(query);


            stmt.setString(1, vs.getId());
            stmt.setInt(2, vs.getVirtualCores());
            stmt.setInt(3, vs.getVirtualRam());
            stmt.setInt(4, vs.getDiskSpace());
            stmt.setString(5,vs.getDiskType());


            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException se){

            return false;
        }

        return true;
    }

    //Get VirtualServer
    public VirtualServer getVirtualServer( String ID) throws SQLException{

        String query =  "SELECT * FROM VirtualServers WHERE ID = ?";
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        VirtualServer toReturn = null;

        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1, ID);

            rslt = stmt.executeQuery();

            while(rslt.next()){
                toReturn = new VirtualServer();
                toReturn.setVirtualCores( rslt.getInt("virtCores"));
                toReturn.setDiskSpace( rslt.getInt("diskSpace"));
                toReturn.setVirtualRam(rslt.getInt("virtRam"));
                toReturn.setDiskType(rslt.getString("disktype"));
                toReturn.setId(ID);

            }
            stmt.close();
        }
        catch (SQLException e){
            return null;
        }

        return toReturn;
    }

    //Delete a particular server with using ID
    public boolean  deleteVirtualServer( String ID) throws SQLException {

        String query = "DELETE FROM VirtualServers WHERE ID = ?";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, ID);

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException se){
            return false;
        }
        return true;
    }



}
