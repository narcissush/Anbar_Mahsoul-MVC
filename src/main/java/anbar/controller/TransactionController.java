package anbar.controller;

import anbar.model.entity.Supplier;
import anbar.model.entity.Transaction;
import anbar.model.service.ProductService;
import anbar.model.service.SupplierService;
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
//        Supplier supplier = new Supplier();
//        ArrayList<String> supplierName = new ArrayList<>();
//        ArrayList<Supplier> supplierList = new ArrayList<>();
//        Supplier selected = supplierTable.getSelectionModel().getSelectedItem();
//        if (selected != null) {
        try {
            TProductNameCmb.getItems().addAll(ProductService.findAll());
            TSupplierNameCmb.getItems().addAll(SupplierService.findAll());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        submitBtn.setOnAction(event -> {
            Transaction transaction =Transaction.builder()
                    .id(1)
                    .build();

        });




    }
}
