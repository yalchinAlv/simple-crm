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
        import model.Customer;

        import java.io.IOException;
        import java.util.ArrayList;

public class CustomerListCusreg {
    Scene previousScene;
    Stage primaryStage;

    @FXML
    private TableView customerTable;
    @FXML private TableColumn<Customer, String> customerNameTab;

    @FXML
    private void initialize() {
        customerNameTab.setCellValueFactory(new PropertyValueFactory<Customer, String>("companyName"));

        ArrayList<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Customer cust = new Customer();
            cust.setCompanyName("Customer " + i);
            customers.add(cust);
        }

        customerTable.getItems().setAll(customers);
    }

    @FXML
    public void clickItem(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create_lead.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        CreateLead controller = fxmlLoader.<CreateLead>getController();

        controller.initData((Customer)customerTable.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
//        System.out.println(((Customer)customerTable.getSelectionModel().getSelectedItem()).getCustomerName());
//
//        Parent tableViewParent = null;
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create_lead.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene tableViewScene = new Scene(tableViewParent);
//
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(tableViewScene);
//        window.show();
//
//        System.out.println("HERE");
    }

    public void setPrevScene(Scene scene, Stage stage) {
        previousScene = scene;
        primaryStage = stage;

        System.out.println(primaryStage);
        System.out.println(previousScene);
    }
}


