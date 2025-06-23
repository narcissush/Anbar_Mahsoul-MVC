package anbar.controller;

import anbar.model.entity.Supplier;
import anbar.model.entity.Transaction;
import anbar.model.service.ProductService;
import anbar.model.service.SupplierService;
import anbar.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML
    private ComboBox TProductNameCmb;
    @FXML
    private ComboBox TSupplierNameCmb;
    @FXML
    private TextField quantityTxt;
    @FXML
    private TextField userTxt;
    @FXML
    private DatePicker submitDate;
    @FXML
    private RadioButton TbuyerRdo,TSellerRdo;
    @FXML
    private Button submitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            TProductNameCmb.getItems().addAll(ProductService.fillTransferProductNameCmb());
            TSupplierNameCmb.getItems().addAll(SupplierService.fillTransferSupplierNameCmb());
            userTxt.setText(String.valueOf(UserService.getLoginUser().getName()));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }



        submitBtn.setOnAction(event -> {
            Transaction transaction =Transaction.builder()
                    .id(1)
                    .build();

        });




    }
}
