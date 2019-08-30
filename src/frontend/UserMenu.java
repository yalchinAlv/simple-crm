package frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Lead;
import model.User;
import model.VirtualServer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserMenu {
    @FXML private Button userProfileButton;
    @FXML private Button listOfCustomersButton;
    @FXML private Button listOfLeadsButton;
    @FXML private Button customerAnalysisButton;
    @FXML private Button salesForecastButton;
    @FXML private Button salesReportButton;
    @FXML private Button warehouseButton;
    @FXML private Button logoutButton;
    @FXML private Button createButton;


    @FXML private BorderPane mainBorderPane;

    @FXML private void initialize(){
        listOfCustomersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer_list.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CustomerListController controller = fxmlLoader.<CustomerListController>getController();
                mainBorderPane.setCenter(root);
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer_registration.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CustomerRegistration controller = fxmlLoader.<CustomerRegistration>getController();
                mainBorderPane.setCenter(root);
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_page.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
                primaryStage.setScene(new Scene(root, 1300, 900));
                primaryStage.show();
            }
        });

        listOfLeadsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leads_list.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LeadsList controller = fxmlLoader.<LeadsList>getController();
                mainBorderPane.setCenter(root);
            }
        });

        customerAnalysisButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer_analysis.fxml"));
                Parent root = null;
                try {
                    root = (Parent)fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CustomerAnalysis controller = fxmlLoader.<CustomerAnalysis>getController();
                mainBorderPane.setCenter(root);
            }
        });

    }
}
