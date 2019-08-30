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
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerRegistration {
    @FXML public Button selectFromExistingButton;
    @FXML public Button nextButton;
    @FXML public BorderPane mainBorderPane;

    @FXML private void initialize(){
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
