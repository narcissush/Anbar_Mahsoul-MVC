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
    private TextField transactionQuantityTxt, productTxt, supplierTxt, transactionUserTxt;

    @FXML
    private DatePicker transactionDate;

    @FXML
    private RadioButton transactionBuyerRdo, transactionSellerRdo;

    @FXML
    private Button transactionSaveBtn;

    @FXML
    private ToggleGroup transactionTypeToggle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (AppState.supplier == null || AppState.product == null) {
                throw new Exception("Supplier or product is null");
            }

            productTxt.setText( AppState.product.productInfo());

            supplierTxt.setText(AppState.supplier.getSupplierName());

            transactionUserTxt.setText(AppState.user.getFullName());
            transactionDate.setValue(LocalDate.now());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }

        transactionSaveBtn.setOnAction(event -> {
            try {

                RadioButton selectedTransactionTypeRdo = (RadioButton) transactionTypeToggle.getSelectedToggle();
                Boolean result = false;

                Transaction transaction = Transaction.builder()
                        .id(1)
                        .product(AppState.product)
                        .supplier(AppState.supplier)
                        .user(AppState.user)
                        .transactionType(TransactionType.valueOf(selectedTransactionTypeRdo.getText()))
                        .transactionQuantity(Integer.parseInt(transactionQuantityTxt.getText()))
                        .transactionDate(Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime())
                        .build();

                ProductService.editQuantity(
                        AppState.product.getId(),
                        Integer.parseInt(transactionQuantityTxt.getText()),
                        selectedTransactionTypeRdo.getText().equals("خرید")?TransactionType.خرید:TransactionType.فروش);

                TransactionService.save(transaction);
                new Alert(Alert.AlertType.INFORMATION, "Supplier Saved", ButtonType.OK).show();
                Stage currentStage = (Stage) transactionSaveBtn.getScene().getWindow();
                currentStage.close();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

    }

    private void resetTransactionForm() throws Exception {
        transactionQuantityTxt.clear();
        transactionUserTxt.setText(AppState.user.getFullName());
        transactionDate.setValue(LocalDate.now());
    }


}
