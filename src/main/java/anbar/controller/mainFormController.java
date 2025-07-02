package anbar.controller;

import anbar.FormManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class mainFormController implements Initializable {
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

                FormManager formManager = new FormManager();
                formManager.showTransactionReportController();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }

    public void setSupplier() {
        selectSupplierLbl.setText(AppState.supplier.getSupplierName());
    }

    public void setProduct() {
        selectedProductLbl.setText(AppState.product.productInfo());
    }

}
