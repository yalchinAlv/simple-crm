package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Lead;
import model.User;
import model.VirtualServer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPage {

    @FXML private Button button;

    @FXML public void buttonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lead_window.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        LeadWindow controller = fxmlLoader.<LeadWindow>getController();
        List<VirtualServer> list = new ArrayList<>();
        //int virtualCores, int virtualRam, int storage, String diskType
        list.add(new VirtualServer(5, 6, 7, "HDD"));
        list.add(new VirtualServer(2, 3, 4, "HDD"));
        list.add(new VirtualServer(1, 2, 3, "SDD"));

        Lead lead = new Lead("My lead", list, LocalDate.now(), LocalDate.now(), 32.2);


        controller.initData(new User("pass", "full", "legal"), lead);
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

//        Parent tableViewParent = null;
//        try {
//            tableViewParent = FXMLLoader.load(getClass().getResource("lead_window.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene tableViewScene = new Scene(tableViewParent);
//
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(tableViewScene);
//        window.show();
    }
}
