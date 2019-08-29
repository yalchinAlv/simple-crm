package frontend;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Lead;
import model.User;
import model.VirtualServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeadWindow {

    User user;
    Lead lead;

    @FXML private Label statusLabel;
    @FXML private VBox serviceVbox1;
    @FXML private VBox serviceVbox2;
    @FXML private VBox serviceVbox3;


    public void initData(User user, Lead lead) {
        this.user = user;
        this.lead = lead;
    }

    @FXML private void initialize() {
        Platform.runLater(() -> {

            statusLabel.setText(lead.getStatus());
            HashMap<String, Integer> map = new HashMap<>();

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
                TextField amount  = new TextField();
                amount.setText("" + map.get(key));
                hBox.getChildren().addAll(vsStats, amount);

                vBox.getChildren().add(hBox);
                i++;
            }
        });
    }

}
