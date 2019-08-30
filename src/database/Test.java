package database;
import model.Lead;
import model.VirtualServer;

import java.beans.Visibility;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;

import java.sql.SQLException;
public class Test{
    public static void main(String[] args) throws SQLException {


        System.out.println("Hello, World");

//        //** Create service for Lead and insert into db
//        VirtualServer sv1 = new VirtualServer(5,5,5,"A");
//        VirtualServer sv2 = new VirtualServer( 4,6,7,"B");
//
//        List<VirtualServer> servList = new ArrayList<VirtualServer>();
//        servList.add(sv1);
//        servList.add(sv2);
//
//        VirtualServerDB vsDB = new VirtualServerDB();
//
//        vsDB.insertVirtualServer(sv1);
//        vsDB.insertVirtualServer(sv2);
//
//        //** Create service for Lead and insert into db
//        VirtualServer dsv1 = new VirtualServer( 2,2, 2, "D");
//
//        List<VirtualServer> dservList = new ArrayList<VirtualServer>();
//        servList.add(dsv1);
//        vsDB.insertVirtualServer(dsv1);
//
//       //** Create dates
//        LocalDate sd = LocalDate.now();
//        LocalDate ed = LocalDate.now();
//        LocalDate dsd = LocalDate.now();
//        LocalDate ded = LocalDate.now();
//
//
//        // Initial Lead
//        Lead lead = new Lead("FirstLead", servList, sd, ed, 1.2 );
//
//        lead.setDemoMonthlyFee(1.3);
//        lead.setDemoStartDate(dsd);
//        lead.setDemoEndDate(ded);
//        lead.setStatus("Pending");
//        lead.setDemoService(dservList);
//
//        // Insert into db
//        LeadDB leadDB = new LeadDB();
//
//        leadDB.insertLead(lead);

          LeadDB leadDB = new LeadDB();
//          Lead myLead = leadDB.getLead("FirstLead");
//
//          System.out.println(myLead.getName());
//
//          List<VirtualServer> vs = myLead.getDemoService();
//          System.out.println(vs.get(0).getId());
          CustomerDB customerDB = new CustomerDB();
          List<String> myList = customerDB.getAllCustomers();
          System.out.println(myList.get(2));
    }




}
