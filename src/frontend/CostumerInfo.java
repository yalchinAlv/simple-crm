package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;

import javax.swing.*;

public class CostumerInfo {
    @FXML
    private Button companyName;
    private Button companyLegalName;
    private Button sector;
    private Button industry;
    private Button country;
    private Button city;
    private Button billingAddress;
    private Button legalAddress;
    private Button acqChannel;
    private Button cpName;
    private Button cpEmail;
    private Button cpLandNum;
    private Button cpCellNum;
    private String cpPhoto;
    private String salesOwner;
    private TextArea legalOwners;
    private TextArea techOwners;
    private SplitMenuButton leads;
}
