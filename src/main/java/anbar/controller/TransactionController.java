package anbar.controller;

import anbar.model.entity.Product;
import anbar.model.entity.Supplier;
import anbar.model.entity.Transaction;

import anbar.model.entity.enums.TransactionType;
import anbar.model.service.ProductService;
import anbar.model.service.SupplierService;
import anbar.model.service.TransactionService;
import anbar.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML
    private ComboBox<Product> transactionProductCmb;
    @FXML
    private ComboBox<Supplier> transactionSupplierCmb;
    @FXML
    private TextField transactionQuantityTxt;
    @FXML
    private TextField transactionUserTxt;
    @FXML
    private DatePicker transactionDate;
    @FXML
    private RadioButton transactionBuyerRdo,transactionSellerRdo;
    @FXML
    private Button transactionSaveBtn;
    @FXML
    private ToggleGroup transactionTypeToggle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {


            transactionProductCmb.getItems().addAll(ProductService.findAll());

            transactionSupplierCmb.getItems().addAll(SupplierService.findAll());
            transactionUserTxt.setText(String.valueOf(UserService.getLoginUser().getName()) +" "+String.valueOf(UserService.getLoginUser().getFamily()) );
            transactionDate.setValue(LocalDate.now());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }



        transactionSaveBtn.setOnAction(event -> {
            try {
//            Product selectedProduct = new Product();
//            Supplier selectedSupplier = new Supplier();
                RadioButton selectedPartyRdo = (RadioButton) transactionTypeToggle.getSelectedToggle();

                Transaction transaction = Transaction.builder()
                        .id(1)
                        .productId(transactionProductCmb.getSelectionModel().getSelectedItem().getId())
                        .supplierId(transactionSupplierCmb.getSelectionModel().getSelectedItem().getId())
                        .userId(UserService.getLoginUser().getId())
                        .transactionType(TransactionType.valueOf(selectedPartyRdo.getText()))
                        .quantity(Integer.parseInt(transactionQuantityTxt.getText()))
                        .transactionDateTime(LocalDateTime.parse(transactionDate.getValue().toString()))
                        .build();
                TransactionService.save(transaction);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();

            }


        });




    }


}
