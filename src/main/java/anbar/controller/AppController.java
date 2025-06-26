package anbar.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class AppController implements Initializable {
    @FXML
    private Menu transactionMenu,helpMenu;
    @FXML
    private MenuItem transactionReport;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        transactionReport.setOnAction(event -> {
            try {
                Stage secondStage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/TransactionReport.fxml")));
                secondStage.setScene(scene);
                secondStage.setTitle("گزارشات انبارگردانی");
                secondStage.show();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });



    }


}
