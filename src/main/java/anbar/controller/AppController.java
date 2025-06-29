package anbar.controller;

import anbar.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AppController implements Initializable {
    @FXML
    private Menu transactionMenu, helpMenu;

    @FXML
    private MenuItem transactionReport;

    @FXML
    private Label userLbl, selectSupplierLbl, selectedProductLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText(AppState.user.getName() + " " + AppState.user.getFamily());
        transactionReport.setOnAction(event -> {
            try {
                Stage secondStage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/TransactionReport.fxml")));
                secondStage.setScene(scene);
                secondStage.setTitle("گزارشات انبارگردانی");
                secondStage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


    }


}
