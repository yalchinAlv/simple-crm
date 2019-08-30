package frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Customer;
import model.Lead;
import model.User;
import model.VirtualServer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeadsList {

    @FXML private TableColumn<Lead, String> leadNameTab;
    @FXML private TableColumn<Lead, String> ownerTab;
    @FXML private TableColumn<Lead, String> statusTab;
    @FXML private TableView leadTableView;

    @FXML private void initialize() {
        leadNameTab.setCellValueFactory(new PropertyValueFactory<Lead, String>("name"));
        ownerTab.setCellValueFactory(new PropertyValueFactory<Lead, String>("ownerName"));
        statusTab.setCellValueFactory(new PropertyValueFactory<Lead, String>("status"));

        ArrayList<Lead> leads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Customer cust = new Customer();
            cust.setCompanyName("Comp name " + i);

            Lead ld = new Lead("Name " + i, new ArrayList<>(), LocalDate.now(), LocalDate.now());
            ld.setOwner(cust);
            ld.setStatus(Lead.Status.CLOSE.toString());
            // String name, List<VirtualServer> service, LocalDate startDate, LocalDate endDate
            leads.add(ld);
        }
        leadTableView.getItems().setAll(leads);
    }

    @FXML
    public void clickItem(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lead_window.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        LeadWindow controller = fxmlLoader.<LeadWindow>getController();
        List<VirtualServer> list = new ArrayList<>();
        //int virtualCores, int virtualRam, int storage, String diskType
        list.add(new VirtualServer(5, 6, 7, "7.2K"));
        list.add(new VirtualServer(2, 3, 4, "10K"));
        list.add(new VirtualServer(1, 2, 3, "SSD"));

        Lead lead = new Lead("My lead", list, LocalDate.now(), LocalDate.now());
        Customer cust = new Customer();
        cust.setCompanyName("COMPANY NAIMU");

        lead.setOwner(cust);
        lead.setStatus(Lead.Status.SIGNUP.toString());

        controller.initData(new User("pass", "full", User.Role.LEGAL_TEAM.toString()), lead);
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
