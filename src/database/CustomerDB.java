package database;
import model.Customer;
import model.Lead;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDB {

    // JDBC driver name and database URL
    private static final String DATABASE_URL = "jdbc:derby:E:\\olddesktop\\Projects\\database\\lib\\SimplifyConsole";
    private static final String USERNAME = "Console";
    private static final String ConnPASSWORD = "simple";
    private Connection connection;

    public CustomerDB() throws SQLException{
        //Create a connection
        connection = DriverManager.getConnection( DATABASE_URL, USERNAME, ConnPASSWORD);
    }

    // Returns true if values is inserted
    public boolean insertCustomer( Customer c)  throws SQLException{

        String query = "INSERT INTO Customers ( ID , cName , cLegalName , sector , industry, country , city , billAddr , legAddr , AqqChan , \n" +
                "\t\t\tcontPsnName , contPsnEmail , contPsnEvNum , contPsnCell, contPsnPhot , salesOwn , legalOwnN1 , legalOwnN2 , \n" +
                "\t\t\tlegalOwnN3 ,techOwnN1 , techOwnN2 , techOwnN3, L1name  , L2name , L3name, L4name , L5name ) " +
                " VALUES (?, ?, ?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            //Use all the Get functio og Customer class
            stmt.setString(1, c.getId());
            stmt.setString(2, c.getCompanyName());
            stmt.setString(3, c.getCompanyLegalName());
            stmt.setString(4, c.getSector());
            stmt.setString(5, c.getIndustry());
            stmt.setString(6, c.getCountry());
            stmt.setString(7, c.getCity());
            stmt.setString(8, c.getBillingAddress());
            stmt.setString(9, c.getLegalAddress());
            stmt.setString(10, c.getAcqChannel());
            stmt.setString(11, c.getCpName());
            stmt.setString(12, c.getCpEmail());
            stmt.setString(13, c.getCpLandNum());
            stmt.setString(14, c.getCpCellNum());
            stmt.setString(15, c.getCpPhoto());
            stmt.setString(16, c.getSalesOwner());

            //Get legal owners
            List<String> LegOwners = c.getLegalOwners();
            int i ;
            for (i = 17 ; i <= 17 + LegOwners.size();i++){
                stmt.setString(i,LegOwners.get(i-17));
            }
            for (;i<= 19; i++){
                stmt.setNull(i, Types.VARCHAR);
            }

            //Get tech owners
            List<String> techOwners = c.getTechOwners();
            for (i = 20 ; i <= 20 + techOwners.size();i++){
                stmt.setString(i,techOwners.get(i-20));
            }
            for (;i<= 22; i++){
                stmt.setNull(i, Types.VARCHAR);
            }

            //Get leads
            List<Lead> leads = c.getLeads();

            for (i = 23 ; i <= 23 + leads.size();i++){
                stmt.setString(i, leads.get(i-23).getName());
            }
            for (;i<= 27; i++){
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

    public Customer getCustomer( String searchName) throws SQLException{

        String query =  "SELECT * FROM customers WHERE cName = ?";
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        Customer toReturn = new Customer();

        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1,searchName);

            rslt = stmt.executeQuery();

            while(rslt.next()){
                toReturn.setId( rslt.getString("ID"));
                toReturn.setCompanyName( rslt.getString("cName"));
                toReturn.setCompanyLegalName( rslt.getString( "cLegalName"));
                toReturn.setSector( rslt.getString("sector"));
                toReturn.setIndustry(rslt.getString("industry"));
                toReturn.setCountry(rslt.getString("country"));
                toReturn.setCity(rslt.getString("city"));
                toReturn.setBillingAddress(rslt.getString("billAddr"));
                toReturn.setLegalAddress(rslt.getString("legAddr"));
                toReturn.setAcqChannel(rslt.getString("AqqChan"));
                toReturn.setCpName(rslt.getString("contPsnName"));
                toReturn.setCpEmail(rslt.getString("contPsnEmail"));
                toReturn.setCpLandNum(rslt.getString("contPsnEvNum"));
                toReturn.setCpCellNum(rslt.getString("contPsnCell"));
                toReturn.setCpPhoto(rslt.getString("contPsnPhot"));
                toReturn.setSalesOwner(rslt.getString("salesOwn"));

                List<String> legOwn  = new ArrayList<String>();

                legOwn.add(rslt.getString("legalOwnN1"));
                legOwn.add(rslt.getString("legalOwnN2"));
                legOwn.add(rslt.getString("legalOwnN3"));

                toReturn.setLegalOwners(legOwn);

                List<String> techOwn  = new ArrayList<String>();

                techOwn.add(rslt.getString("techOwnN1"));
                techOwn.add(rslt.getString("techOwnN2"));
                techOwn.add(rslt.getString("techOwnN3"));

                toReturn.setLegalOwners(techOwn);


                List<String> leadNames  = new ArrayList<String>();

                leadNames.add(rslt.getString("L1name"));
                leadNames.add(rslt.getString("L2name"));
                leadNames.add(rslt.getString("L3name"));
                leadNames.add(rslt.getString("L4name"));
                leadNames.add(rslt.getString("L5name"));


                List<Lead> leads = new ArrayList<Lead>();
                //TOOO ADDDD ADDD LEADS



            }
            stmt.close();
        }
        catch (SQLException e){
            return null;
        }

        return toReturn;
    }

    // Give all the customers
    public List<String> getAllCustomersNames(){
        String query = "select cname from Customers";
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        List<String> names = new ArrayList<String>();

        try{
            stmt = connection.prepareStatement(query);

            rslt = stmt.executeQuery();

            while(rslt.next()){

                names.add(rslt.getString("cName"));
            }
            stmt.close();
        }
        catch (SQLException e){
            return null;
        }

        return names;
    }

    public boolean deleteCustomer(String toDelete){
        String query = "DELETE FROM Customers WHERE cName=?";
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        boolean result = false;

        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1, toDelete);


            result = stmt.execute();
            stmt.close();
        }
        catch (SQLException e){
            return false;
        }

        return result;
    }
}
