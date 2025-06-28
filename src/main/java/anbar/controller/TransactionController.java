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
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
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

                RadioButton selectedtransactionTypeRdo = (RadioButton) transactionTypeToggle.getSelectedToggle();
                Boolean result = false;

                Transaction transaction = Transaction.builder()
                        .id(1)
                        .product(transactionProductCmb.getSelectionModel().getSelectedItem())
                        .supplier(transactionSupplierCmb.getSelectionModel().getSelectedItem())
                        .user(UserService.getLoginUser())
                        .transactionType(TransactionType.valueOf(selectedtransactionTypeRdo.getText()))
                        .transactionQuantity(Integer.parseInt(transactionQuantityTxt.getText()))
                        .transactionDate(Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime())
                        .build();

                if (selectedtransactionTypeRdo.getText().equals("خرید"))
                {
                    result=ProductService.editQuantity(transactionProductCmb.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(transactionQuantityTxt.getText()), 1);
                }
                else if(selectedtransactionTypeRdo.getText().equals("فروش"))
                {
                    result= ProductService.editQuantity(transactionProductCmb.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(transactionQuantityTxt.getText()), 2);
                }
                if(result){
                    TransactionService.save(transaction);
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved", ButtonType.OK).show();
                    Stage currentStage = (Stage) transactionSaveBtn.getScene().getWindow();
                    currentStage.close();
                }
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();

            }
        });

    }
    private void resetTransactionForm() throws Exception {
        transactionQuantityTxt.clear();
        transactionUserTxt.setText(String.valueOf(UserService.getLoginUser().getName()) +" "+String.valueOf(UserService.getLoginUser().getFamily()) );
        transactionDate.setValue(LocalDate.now());
    }


}
