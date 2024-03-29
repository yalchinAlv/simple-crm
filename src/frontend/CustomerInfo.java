package frontend;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import model.Customer;

import javax.swing.*;

public class CustomerInfo {
    @FXML private Label companyName;
    @FXML private Label companyLegalName;
    @FXML private Label sector;
    @FXML private Label industry;
    @FXML private Label country;
    @FXML private Label city;
    @FXML private Label billingAddress;
    @FXML private Label legalAddress;
    @FXML private Label acqChannel;
    @FXML private Label cpName;
    @FXML private Label cpEmail;
    @FXML private Label cpLandNum;
    @FXML private Label cpCellNum;
    @FXML private Label cpPhoto;
    @FXML private Label salesOwner;
    @FXML private TextArea legalOwners;
    @FXML private TextArea techOwners;
    @FXML private SplitMenuButton leads;

    Customer customer;

    public void initData(Customer customer) {
        this.customer = customer;
    }

    @FXML private void initialize() {
        Platform.runLater(() -> {

        });
    }
}
