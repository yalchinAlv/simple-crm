package frontend;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Lead;
import model.User;
import model.VirtualServer;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LeadWindow {

    User user;
    Lead lead;

    @FXML private ComboBox<String> statusComboBox;
    @FXML private VBox serviceVbox1;
    @FXML private VBox serviceVbox2;
    @FXML private VBox serviceVbox3;
    @FXML private Button newServiceBtn;
    @FXML private Button customerButton;
    @FXML private Label feeWithoutVatLabel;
    @FXML private Label vatLabel;
    @FXML private Label totalLabel;
    @FXML private TextField discountTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker stopDatePicker;
    @FXML private Button updateButton;
    @FXML private VBox mainVbox;

    HashMap<String, Integer> map;

    public void initData(User user, Lead lead) {
        this.user = user;
        this.lead = lead;
    }

    @FXML private void initialize() {
        Platform.runLater(() -> {
            ArrayList<String> statuses = new ArrayList<>();
            for (Lead.Status status : Lead.Status.values()){
                statuses.add(status.toString());
            }
            statusComboBox.setItems(FXCollections.observableArrayList(statuses));
            statusComboBox.setValue(lead.getStatus());

            statusComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> ov,
                                    final String oldvalue, final String newvalue)
                {
                    lead.setStatus(newvalue);
                }
            });
            System.out.println(statusComboBox.getValue());
            if (statusComboBox.getValue().equals(Lead.Status.PROPOSE.toString())) {
                Button sendPDF = new Button("Generate & Send PDF");
                sendPDF.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        // TODO: CALL AYDEMIR'S METHOD
                    }
                });
                System.out.println("adding");
                mainVbox.getChildren().add(sendPDF);
            }

            if (statusComboBox.getValue().equals(Lead.Status.SIGNUP.toString())) {
                Button finish = new Button("Finish");
                finish.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        // TODO: FINISH
                    }
                });
                System.out.println("adding");
                mainVbox.getChildren().add(finish);
            }

            discountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("textfield changed from " + oldValue + " to " + newValue);
                double monthlyFeeWithoutDiscount = lead.getMonthlyFee();
                monthlyFeeWithoutDiscount = monthlyFeeWithoutDiscount * (1 - 0.01 * Integer.parseInt(newValue.equals("") ? "0" : newValue));

                feeWithoutVatLabel.setText("Fee without VAT: " + monthlyFeeWithoutDiscount);
                vatLabel.setText("VAT: " + (monthlyFeeWithoutDiscount * 0.18));
                totalLabel.setText("Total: " + (monthlyFeeWithoutDiscount + (monthlyFeeWithoutDiscount * 0.18)));
            });

            startDatePicker.setValue(lead.getStartDate());
            stopDatePicker.setValue(lead.getEndDate());
            feeWithoutVatLabel.setText("Fee without VAT: " + lead.getMonthlyFee());
            vatLabel.setText("VAT: " + (lead.getMonthlyFee() * 0.18));
            totalLabel.setText("Total: " + (lead.getMonthlyFee() + (lead.getMonthlyFee() * 0.18)));
            customerButton.setText(lead.getOwner().getCompanyName());



            map = new HashMap<>();

            for (VirtualServer vs : lead.getService()) {
                String key = "(" + vs.getVirtualCores() + "x2.4GHz/"
                        + vs.getVirtualRam() + "RAM/"
                        + vs.getDiskSpace() + "GB" + vs.getDiskType() + ")";

                if (map.containsKey(key))
                    map.put(key, map.get(key) + 1);
                else
                    map.put(key, 1);
            }

            int i = 0;
            for (String key : map.keySet()) {
                VBox vBox;
                if (i / 5 == 0)
                    vBox = serviceVbox1;
                else if (i / 5 == 1)
                    vBox = serviceVbox2;
                else
                    vBox = serviceVbox3;

                HBox hBox = new HBox();
                Label vsStats = new Label(key);

                int virtCores = Integer.parseInt(vsStats.getText().substring(vsStats.getText().indexOf("(") + 1, vsStats.getText().indexOf("x")));
                int virtRam = Integer.parseInt(vsStats.getText().substring(vsStats.getText().indexOf("/") + 1, vsStats.getText().indexOf("RAM")));
                int storage = Integer.parseInt(vsStats.getText().substring(vsStats.getText().lastIndexOf("/") + 1, vsStats.getText().indexOf("GB")));
                String diskType = vsStats.getText().substring(vsStats.getText().lastIndexOf("GB") + 2, vsStats.getText().indexOf(")"));


                TextField amount  = new TextField();
                amount.setPrefWidth(50);
                amount.setText("" + map.get(key));
                amount.textProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println("textfield changed from " + oldValue + " to " + newValue);

                    Integer newValInt = newValue.equals("") ? 0 : Integer.parseInt(newValue);
                    Integer oldValInt = oldValue.equals("") ? 0 : Integer.parseInt(oldValue);

                    //nt virtualCores, int virtualRam, int storage, String diskType
                    if (newValInt > oldValInt) {
                        for (int j = 0; j < newValInt - oldValInt; j++) {
                            lead.getService().add(new VirtualServer(virtCores, virtRam, storage, diskType));
                        }

//                        double monthlyFeeWithoutDiscount = lead.getMonthlyFee();
//                        monthlyFeeWithoutDiscount = monthlyFeeWithoutDiscount * (1 - 0.01 * Integer.parseInt(newValue));

                        lead.recalculateMonthlyFee();
                        feeWithoutVatLabel.setText("Fee without VAT: " + lead.getMonthlyFee());
                        vatLabel.setText("VAT: " + (lead.getMonthlyFee() * 0.18));
                        totalLabel.setText("Total: " + (lead.getMonthlyFee() + (lead.getMonthlyFee() * 0.18)));
                    }
                    else if (newValInt < oldValInt) {
                        int count = oldValInt - newValInt;

                        for (int j = 0; j < lead.getService().size(); j++) {
                            VirtualServer tmpServer = lead.getService().get(j);
                            if (count > 0 && tmpServer.getDiskType().equals(diskType) && tmpServer.getDiskSpace() == storage && tmpServer.getVirtualRam() == virtRam && tmpServer.getVirtualCores() == virtCores) {
                                lead.getService().remove(j);
                                j--;
                                count--;
                            }
                        }

                        lead.recalculateMonthlyFee();
                        feeWithoutVatLabel.setText("Fee without VAT: " + lead.getMonthlyFee());
                        vatLabel.setText("VAT: " + (lead.getMonthlyFee() * 0.18));
                        totalLabel.setText("Total: " + (lead.getMonthlyFee() + (lead.getMonthlyFee() * 0.18)));
                    }
//                    feeWithoutVatLabel.setText("Fee without VAT: " + lead.getMonthlyFee());
//                    vatLabel.setText("VAT: " + (lead.getMonthlyFee() * 0.18));
//                    totalLabel.


//                    double monthlyFeeWithoutDiscount = Double.parseDouble(feeWithoutVatLabel.getText());
//                    monthlyFeeWithoutDiscount = monthlyFeeWithoutDiscount * (1 - 0.01 * Integer.parseInt(newValue));
//
//                    feeWithoutVatLabel.setText("Fee without VAT: " + monthlyFeeWithoutDiscount);
//                    vatLabel.setText("VAT: " + (monthlyFeeWithoutDiscount * 0.18));
//                    totalLabel.setText("Total: " + (monthlyFeeWithoutDiscount + (monthlyFeeWithoutDiscount * 0.18)));
                });

                Button editStorage = new Button("Edit");
                editStorage.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        // Create the custom dialog.
                        Dialog<Pair<String, String>> dialog = new Dialog<>();
                        dialog.setTitle("Storage");
                        dialog.setHeaderText("Edit Storage");

                        // Set the button types.
                        ButtonType loginButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                        // Create the username and password labels and fields.
                        GridPane grid = new GridPane();
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(20, 150, 10, 10));

                        TextField storageField = new TextField();
                        storageField.setPromptText("Storage");

                        ComboBox<String> diskType = new ComboBox<>();
                        ArrayList<String> types = new ArrayList<>();
                        types.add("7.2K");
                        types.add("10K");
                        types.add("SSD");
                        diskType.setItems(FXCollections.observableArrayList(types));

                        grid.add(new Label("Storage:"), 0, 1);
                        grid.add(storageField, 1, 1);
                        grid.add(new Label("Disk Type:"), 0, 2);
                        grid.add(diskType, 1, 2);

                        // Enable/Disable login button depending on whether a username was entered.
                        //Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
                        //loginButton.setDisable(true);

//        // Do some validation (using the Java 8 lambda syntax).
//        username.textProperty().addListener((observable, oldValue, newValue) -> {
//            loginButton.setDisable(newValue.trim().isEmpty());
//        });

                        dialog.getDialogPane().setContent(grid);

                        // Request focus on the username field by default.
                        //Platform.runLater(() -> username.requestFocus());

                        // Convert the result to a username-password-pair when the login button is clicked.
                        dialog.setResultConverter(dialogButton -> {
                            if (dialogButton == loginButtonType) {
                                return new Pair<>(storageField.getText(), diskType.getSelectionModel().getSelectedItem());
                            }
                            return null;
                        });

                        Optional<Pair<String, String>> result = dialog.showAndWait();

                        Integer obj = map.remove(key);
                        String str = key;
                        str = str.substring(0, str.lastIndexOf("/") + 1);
                        str += result.get().getKey() + "GB" + result.get().getValue() + ")";
                        map.put(str, obj);

                        int virtCores1 = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf("x")));
                        int virtRam1 = Integer.parseInt(str.substring(str.indexOf("/") + 1, str.indexOf("RAM")));
                        int virtStorage1 = Integer.parseInt(str.substring(str.lastIndexOf("/") + 1, str.indexOf("GB")));
                        String virtDiskType1 = str.substring(str.lastIndexOf("GB") + 2, str.indexOf(")"));

                        lead.getService().add(new VirtualServer(virtCores1, virtRam1, virtStorage1, virtDiskType1));
                        lead.recalculateMonthlyFee();

                        for (int j = 0; j < lead.getService().size(); j++) {
                            VirtualServer tmpServer = lead.getService().get(j);
                            if (tmpServer.getDiskType().equals(diskType) && tmpServer.getDiskSpace() == storage && tmpServer.getVirtualRam() == virtRam && tmpServer.getVirtualCores() == virtCores) {
                                lead.getService().remove(j);
                                lead.recalculateMonthlyFee();
                                break;
                            }
                        }
                        vsStats.setText(str);
                        lead.recalculateMonthlyFee();
                        feeWithoutVatLabel.setText("Fee without VAT: " + lead.getMonthlyFee());
                        vatLabel.setText("VAT: " + (lead.getMonthlyFee() * 0.18));
                        totalLabel.setText("Total: " + (lead.getMonthlyFee() + (lead.getMonthlyFee() * 0.18)));
                    }
                });
                hBox.getChildren().addAll(vsStats, amount, editStorage);

                vBox.getChildren().add(hBox);

                newServiceBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        // Create the custom dialog.
                        Dialog<Pair<String, String>> dialog = new Dialog<>();
                        dialog.setTitle("New VS");
                        dialog.setHeaderText("Add a new VS");

                        // Set the button types.
                        ButtonType loginButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                        // Create the username and password labels and fields.
                        GridPane grid = new GridPane();
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setPadding(new Insets(20, 150, 10, 10));

                        ComboBox<String> comboBox = new ComboBox<>();
                        ArrayList<String> choices = new ArrayList<>();
                        choices.add("1x2.4GHz/2RAM");
                        choices.add("2x2.4GHz/4RAM");
                        choices.add("4x2.4GHz/8RAM");
                        choices.add("4x2.4GHz/16RAM");
                        choices.add("8x2.4GHz/16RAM");
                        choices.add("8x2.4GHz/32RAM");
                        choices.add("8x2.4GHz/64RAM");
                        choices.add("12x2.4GHz/24RAM");
                        choices.add("12x2.4GHz/36RAM");
                        choices.add("24x2.4GHz/48RAM");
                        choices.add("24x2.4GHz/96RAM");
                        choices.add("32x2.4GHz/64RAM");
                        choices.add("32x2.4GHz/128RAM");
                        choices.add("32x2.4GHz/192RAM");
                        choices.add("32x2.4GHz/256RAM");
                        comboBox.setItems(FXCollections.observableArrayList(choices));

                        TextField storage = new TextField();
                        storage.setPromptText("Storage");

                        ComboBox<String> diskType = new ComboBox<>();
                        ArrayList<String> types = new ArrayList<>();
                        types.add("7.2K");
                        types.add("10K");
                        types.add("SSD");
                        diskType.setItems(FXCollections.observableArrayList(types));

                        grid.add(new Label("VS:"), 0, 0);
                        grid.add(comboBox, 1, 0);
                        grid.add(new Label("Storage:"), 0, 1);
                        grid.add(storage, 1, 1);
                        grid.add(new Label("Disk Type:"), 0, 2);
                        grid.add(diskType, 1, 2);

                        // Enable/Disable login button depending on whether a username was entered.
                        //Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
                        //loginButton.setDisable(true);

//        // Do some validation (using the Java 8 lambda syntax).
//        username.textProperty().addListener((observable, oldValue, newValue) -> {
//            loginButton.setDisable(newValue.trim().isEmpty());
//        });

                        dialog.getDialogPane().setContent(grid);

                        // Request focus on the username field by default.
                        //Platform.runLater(() -> username.requestFocus());

                        // Convert the result to a username-password-pair when the login button is clicked.
                        dialog.setResultConverter(dialogButton -> {
                            if (dialogButton == loginButtonType) {
                                return new Pair<>("(" + comboBox.getSelectionModel().getSelectedItem() + "/"
                                        + storage.getText() + "GB"
                                        + diskType.getSelectionModel().getSelectedItem() + ")", null);
                            }
                            return null;
                        });

                        Optional<Pair<String, String>> result = dialog.showAndWait();

                        System.out.println(result.get().getKey());

                        if (map.containsKey(result.get().getKey())) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("This VS already exists!");

                            alert.showAndWait();
                        } else {
                            map.put(result.get().getKey(), 1);
                            VBox tmpBox = new VBox();
                            if (serviceVbox1.getChildren().size() < 5)
                                tmpBox = serviceVbox1;
                            else if (serviceVbox2.getChildren().size() < 5)
                                tmpBox = serviceVbox2;
                            else if (serviceVbox3.getChildren().size() < 5)
                                tmpBox = serviceVbox3;

                            HBox hBox = new HBox();
                            Label vsStats = new Label(result.get().getKey());
                            TextField amount  = new TextField();
                            amount.setPrefWidth(50);
                            amount.setText("" + map.get(result.get().getKey()));

                            hBox.getChildren().addAll(vsStats, amount);

                            tmpBox.getChildren().add(hBox);
                        }

                    }
                });
                i++;
            }

            if (user.getRole().equals(User.Role.LEGAL_TEAM.toString()) &&
                    (lead.getStatus().equals(Lead.Status.CLOSE.toString()) || lead.getStatus().equals(Lead.Status.SIGNUP.toString()))) {
                statusComboBox.setDisable(true);
                customerButton.setDisable(true);
                startDatePicker.setDisable(true);
                stopDatePicker.setDisable(true);
                serviceVbox1.setDisable(true);
                serviceVbox2.setDisable(true);
                serviceVbox3.setDisable(true);
                newServiceBtn.setDisable(true);
                discountTextField.setDisable(true);
                updateButton.setDisable(true);
            }

            if (user.getRole().equals(User.Role.TECHNICAL_TEAM.toString()) &&
                    (lead.getStatus().equals(Lead.Status.DEMO.toString())
                            || lead.getStatus().equals(Lead.Status.PROPOSE.toString())
                            || lead.getStatus().equals(Lead.Status.PROVE.toString())
                            || lead.getStatus().equals(Lead.Status.CLOSE.toString())
                            || lead.getStatus().equals(Lead.Status.SIGNUP.toString())
                    )) {
                statusComboBox.setDisable(true);
                customerButton.setDisable(true);
                startDatePicker.setDisable(true);
                stopDatePicker.setDisable(true);
                serviceVbox1.setDisable(true);
                serviceVbox2.setDisable(true);
                serviceVbox3.setDisable(true);
                newServiceBtn.setDisable(true);
                discountTextField.setDisable(true);
                updateButton.setDisable(true);
            }

            if (user.getRole().equals(User.Role.FINANCE.toString()) &&
                    (lead.getStatus().equals(Lead.Status.SIGNUP.toString())
                    )) {
                statusComboBox.setDisable(true);
                customerButton.setDisable(true);
                startDatePicker.setDisable(true);
                stopDatePicker.setDisable(true);
                serviceVbox1.setDisable(true);
                serviceVbox2.setDisable(true);
                serviceVbox3.setDisable(true);
                newServiceBtn.setDisable(true);
                discountTextField.setDisable(true);
                updateButton.setDisable(true);
            }

            if (user.getRole().equals(User.Role.TECHNICAL_SUPPORT_TEAM.toString()) &&
                    (lead.getStatus().equals(Lead.Status.DEMO.toString())
                            || lead.getStatus().equals(Lead.Status.PROPOSE.toString())
                            || lead.getStatus().equals(Lead.Status.PROVE.toString())
                            || lead.getStatus().equals(Lead.Status.CLOSE.toString())
                            || lead.getStatus().equals(Lead.Status.SIGNUP.toString())
                    )) {
                statusComboBox.setDisable(true);
                customerButton.setDisable(true);
                startDatePicker.setDisable(true);
                stopDatePicker.setDisable(true);
                serviceVbox1.setDisable(true);
                serviceVbox2.setDisable(true);
                serviceVbox3.setDisable(true);
                newServiceBtn.setDisable(true);
                discountTextField.setDisable(true);
                updateButton.setDisable(true);
            }

            if (user.getRole().equals(User.Role.FINANCE.toString()) &&
                    (lead.getStatus().equals(Lead.Status.SIGNUP.toString())
                    )) {
                statusComboBox.setDisable(true);
                customerButton.setDisable(true);
                startDatePicker.setDisable(true);
                stopDatePicker.setDisable(true);
                serviceVbox1.setDisable(true);
                serviceVbox2.setDisable(true);
                serviceVbox3.setDisable(true);
                newServiceBtn.setDisable(true);
                discountTextField.setDisable(true);
                updateButton.setDisable(true);
            }

            updateButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent event) {
                    if (lead.getStatus().equals(Lead.Status.DEMO.toString())) {
                        lead.setDemoStartDate(startDatePicker.getValue());
                        lead.setDemoEndDate(stopDatePicker.getValue());
                    }
                    else {
                        lead.setStartDate(startDatePicker.getValue());
                        lead.setEndDate(stopDatePicker.getValue());
                    }

                    // TODO EVERYTHING SAME HERE
                }
            });
        });

        customerButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (lead.getStatus().equals(Lead.Status.NEW)) {
                    lead.setStatus(Lead.Status.INITIATE.toString());
                    System.out.println("INITIATED: " + lead.getStatus());
                }


            }
        });
    }

    @FXML
    public void addNewService(ActionEvent event) {


    }
}
