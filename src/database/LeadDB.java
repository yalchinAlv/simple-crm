package database;

import model.Lead;
import model.VirtualServer;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

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

        String query = "INSERT INTO Leads ( name , status , sDate , eDate , mFee  , VS1 , VS2 , VS3 , VS4 , VS5 ,\n" +
                "\t\t\t\t\n" +
                "\t\t\tdsDate , deDate , dmFee , dVS1 , dVS2 , dVS3 , dVS4 , dVS5  )\n " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);


            stmt.setString(1, ld.getName());
            stmt.setString( 2, ld.getStatus());
            stmt.setString( 3, ld.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString( 4, ld.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setDouble( 5, ld.getMonthlyFee());

            List<VirtualServer> lvs = ld.getService();

            int i;

            for (i = 6 ; i <= 6 + lvs.size()-1;i++){

                stmt.setString(i, lvs.get(i-6).getId());
            }
            for (;i<= 10; i++){
                stmt.setNull(i, Types.VARCHAR);
            }

            stmt.setString( 11, ld.getDemoStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString( 12, ld.getDemoEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setDouble( 13, ld.getDemoMonthlyFee());

            List<VirtualServer> dlvs = ld.getDemoService();

            for (i = 14 ; i <= 14 + dlvs.size()-1;i++){

                stmt.setString(i, dlvs.get(i-14).getId());
            }
            for (;i<= 18; i++){
                stmt.setNull(i, Types.VARCHAR);
            }

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException se){

            return false;
        }

        return true;
    }



    //Get VirtualServer
    public Lead getLead( String searchName) throws SQLException{

        String query =  "SELECT * FROM Leads WHERE name = ?";
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        Lead toReturn = null;

        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1, searchName);

            rslt = stmt.executeQuery();

            while(rslt.next()){
                toReturn = new Lead();
                toReturn.setName(rslt.getString("name"));
                toReturn.setStatus(rslt.getString("status"));
                toReturn.setMonthlyFee(rslt.getFloat("mFee"));
                toReturn.setDemoMonthlyFee(rslt.getFloat("dmFee"));

                toReturn.setStartDate( LocalDate.parse( rslt.getString("sDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd") ));
                toReturn.setEndDate(  LocalDate.parse( rslt.getString("eDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd") ));
                toReturn.setDemoStartDate( LocalDate.parse( rslt.getString("dsDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd") ));
                toReturn.setDemoEndDate( LocalDate.parse( rslt.getString("deDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd") ));

                List<VirtualServer>  srvs  = new ArrayList<VirtualServer>();
                VirtualServerDB vsdb = new VirtualServerDB();

                // Get first VirtualServer
                String serverName = rslt.getString("VS1");
                if ( serverName != null){
                    srvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("VS2");
                if ( serverName != null){
                    srvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("VS3");
                if ( serverName != null){
                    srvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("VS4");
                if ( serverName != null){
                    srvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("VS4");
                if ( serverName != null){
                    srvs.add( vsdb.getVirtualServer(serverName));
                }

                toReturn.setService(srvs);

                List<VirtualServer>  dsrvs  = new ArrayList<VirtualServer>();
                //DEMO
                serverName = rslt.getString("DVS1");
                if ( serverName != null){
                    dsrvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("DVS2");
                if ( serverName != null){
                    dsrvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("DVS3");
                if ( serverName != null){
                    dsrvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("DVS4");
                if ( serverName != null){
                    dsrvs.add( vsdb.getVirtualServer(serverName));
                }
                serverName = rslt.getString("DVS5");
                if ( serverName != null){
                    dsrvs.add( vsdb.getVirtualServer(serverName));
                }

                toReturn.setDemoService(dsrvs);

            }
            stmt.close();
        }
        catch (SQLException e){
            return null;
        }

        return toReturn;
    }

    public boolean  deleteLead( String nameToDel) throws SQLException {

        String query = "DELETE FROM Leads WHERE ID = ?";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nameToDel);

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException se){
            return false;
        }
        return true;
    }

    public boolean  updateLead( Lead ld) throws SQLException {

        boolean del = deleteLead( ld.getName());
        boolean ins = insertLead( ld);

        return del && ins;
    }
}
