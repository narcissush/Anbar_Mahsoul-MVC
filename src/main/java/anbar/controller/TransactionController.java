package anbar.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    }
}
