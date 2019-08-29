package frontend;

//import model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerListController {
    Scene previousScene;
    Stage primaryStage;

    @FXML private TableView customerTable;
    @FXML private TableColumn<Customer, String> customerNameTab;

    @FXML
    private void initialize() {
        customerNameTab.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));

        ArrayList<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customers.add(new Customer("Customer #" + i));
        }

        customerTable.getItems().setAll(customers);
    }

    @FXML
    public void clickItem(MouseEvent event) {
        System.out.println(((Customer)customerTable.getSelectionModel().getSelectedItem()).getCustomerName());

        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("test_page.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

        System.out.println("HERE");
    }

    public void setPrevScene(Scene scene, Stage stage) {
        previousScene = scene;
        primaryStage = stage;

        System.out.println(primaryStage);
        System.out.println(previousScene);
    }


    public class Customer {
        String customerName;

        public Customer(String name) {
            customerName = name;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }
}


