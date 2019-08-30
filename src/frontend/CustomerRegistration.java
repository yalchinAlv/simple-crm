package frontend;

import database.CustomerDB;
import database.LeadDB;
import database.VirtualServerDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerRegistration {
    @FXML public Button selectFromExistingButton;
    @FXML public Button nextButton;
    @FXML public BorderPane mainBorderPane;
    @FXML public TextField compNameText;
    @FXML public TextField compLegalNameText;
    @FXML public TextField countryName;
    @FXML public TextField cityName;
    @FXML public TextField billAddressText;
    @FXML public TextField legalAddressText;
    @FXML public TextField acqChannelText;
    @FXML public TextField cpNameText;
    @FXML public TextField cpEmailText;
    @FXML public TextField cpLanNum;
    @FXML public TextField cpCellNum;
    @FXML public TextField salesOwnerText;
    @FXML public TextField legalOwnerText;
    @FXML public TextField techOwnerText;
    @FXML private ComboBox comboBoxSector;
    @FXML private ComboBox comboBoxIndustry;

    @FXML private void initialize(){

        comboBoxSector.getItems().addAll("Government", "Private");
        comboBoxIndustry.getItems().addAll("Accountants", "Advertising/Public Relations", "Aerospace, Defense Contractors",
                "Agribusiness", "Agricultural Services & Products", "Agriculture", "Air Transport", "Air Transport Unions", "Airlines",
                "Banks, Commercial", "Business Services", "Colleges, Universities & Schools", "Commercial TV & Radio Stations", "Defense",
                "Dentists", "Finance, Insurance & Real Estate", "Manufacturing, Misc", "Medical Supplies");

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create_lead.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CreateLead controller = fxmlLoader.<CreateLead>getController();
                mainBorderPane = (BorderPane) nextButton.getScene().getRoot();
                mainBorderPane.setCenter(root);
            }
        });

        selectFromExistingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer_list_cusreg.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CustomerListCusreg controller = fxmlLoader.<CustomerListCusreg>getController();
                mainBorderPane = (BorderPane) selectFromExistingButton.getScene().getRoot();
                mainBorderPane.setCenter(root);
            }
        });
    }
}
