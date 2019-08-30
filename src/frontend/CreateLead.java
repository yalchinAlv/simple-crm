package frontend;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreateLead {

    private int numOfVrs;
    // Buttons
    @FXML private Button incButton;
    @FXML private Button decButton;
    @FXML private Button addButton;
    @FXML private Button nextButton;

    // TextFields
    @FXML private TextField textFieldVRAmount;
    @FXML private TextField textFieldStorageSize;

    // ComboBoxes
    @FXML private ComboBox comboBoxVRs;
    @FXML private ComboBox comboBoxStorage;

    // TextArea
    @FXML private TextArea textAreaAddedLeads;

    @FXML public BorderPane mainBorderPane;


    @FXML public void initialize(){

        comboBoxVRs.getItems().addAll("CPU: 1 RAM: 2", "CPU: 2 RAM: 4", "CPU: 4 RAM: 8", "CPU: 4 RAM: 16", "CPU: 8 RAM: 16",
                "CPU: 8 RAM: 32", "CPU: 8 RAM: 64", "CPU: 12 RAM: 24", "CPU: 12 RAM: 36", "CPU: 24 RAM: 48", "CPU: 24 RAM: 96",
                "CPU: 32 RAM: 64", "CPU: 32 RAM: 128", "CPU: 32 RAM: 192", "CPU: 32 RAM: 256");
        comboBoxStorage.getItems().addAll("7.2K", "10K", "SSD");
        numOfVrs = 0;
        textFieldVRAmount.setText(numOfVrs + "");

        incButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numOfVrs = Integer.parseInt(textFieldVRAmount.getText());
                numOfVrs++;
                textFieldVRAmount.setText(numOfVrs + "");
            }
        });

        decButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numOfVrs = Integer.parseInt(textFieldVRAmount.getText());
                if(numOfVrs > 0){
                    numOfVrs--;
                    textFieldVRAmount.setText(numOfVrs + "");
                }
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                double monthlyFee = 0;
//                int size = Integer.parseInt(textFieldStorageSize.getText());
//                int VSCount = Integer.parseInt(textFieldVRAmount.getText());
//                if(comboBoxStorage.getValue() == "7.2K")
//                    monthlyFee = 0.05 * size * VSCount;
//                else if (comboBoxStorage.getValue() == "10K")
//                    monthlyFee = 0.07 * size * VSCount;
//                else
//                    monthlyFee = 0.09 * size * VSCount;
                String leadText = comboBoxVRs.getValue() + ",  Amount: " + textFieldVRAmount.getText() + ",  " +
                        comboBoxStorage.getValue() + ": " + textFieldStorageSize.getText() + " GB" + "\n";
                if(Integer.parseInt(textFieldVRAmount.getText()) > 0 && comboBoxVRs.getValue() != null && comboBoxStorage.getValue() != null)
                    textAreaAddedLeads.appendText(leadText);
            }
        });

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainBorderPane = (BorderPane) nextButton.getScene().getRoot();
                mainBorderPane.setCenter(null);
            }
        });
    }
}
