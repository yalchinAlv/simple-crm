package utilities;

import database.CustomerDB;
import model.Customer;
import model.Lead;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerAnalysis {

    public static List<Customer> getCustomers() {

        try {
            CustomerDB customerDB = new CustomerDB();

            List<Customer> customers = new ArrayList<>();
            List<Customer> allCustomers = customerDB.getAllCustomers();
            int count = 0;
            for (Customer customer : allCustomers) {
                for (Lead lead : customer.getLeads()) {
                    if (lead.getStatus().equals(Lead.Status.SIGNUP.toString())) {
                        count++;
                        customers.add(customer);
                        break;
                    }
                }
            }

            return customers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Customer> getPotentialCustomers() {

        try {
            CustomerDB customerDB = new CustomerDB();

            List<Customer> potentialCustomers = new ArrayList<>();
            List<Customer> allCustomers = customerDB.getAllCustomers();
            int count = 0;
            for (Customer customer : allCustomers) {
                for (Lead lead : customer.getLeads()) {
                    if (!lead.getStatus().equals(Lead.Status.SIGNUP.toString())) {
                        count++;
                        potentialCustomers.add(customer);
                        break;
                    }
                }
            }

            return potentialCustomers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
