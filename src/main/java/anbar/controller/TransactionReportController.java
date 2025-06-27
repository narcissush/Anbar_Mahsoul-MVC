package anbar.controller;

import anbar.model.entity.Product;
import anbar.model.entity.Transaction;
import anbar.model.service.TransactionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionReportController implements Initializable {

    @FXML
    private ComboBox<String> searchItemCmb1;
    @FXML private ComboBox<String> searchItemCmb2;
    @FXML private TextField searchItemTxt;
    @FXML private Button searchBtn;
    @FXML private Button excelBtn;
    @FXML private Button cancleBtn;
    @FXML private TableView<Transaction> transactionTableView;

    @FXML private TableColumn<Transaction, Integer> id;
    @FXML private TableColumn<Transaction, String> category;
    @FXML private TableColumn<Transaction, String> brand;
    @FXML private TableColumn<Transaction, String> model;
    @FXML private TableColumn<Transaction, Double> price;
    @FXML private TableColumn<Transaction, Integer> totalQuantity;
    @FXML private TableColumn<Transaction, String> SupplierName;
    @FXML private TableColumn<Transaction, String> personType;
    @FXML private TableColumn<Transaction, String> nationalId;
    @FXML private TableColumn<Transaction, String> phoneNumber;
    @FXML private TableColumn<Transaction, String> mobileNumber;
    @FXML private TableColumn<Transaction, String> Username;
    @FXML private TableColumn<Transaction, String> family;
    @FXML private TableColumn<Transaction, String> transactionType;
    @FXML private TableColumn<Transaction, Integer> transactionQuantity;
    @FXML private TableColumn<Transaction, String> transactionDate;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //fillTable--------------------------------------------------------------
    private void fillTransactionTable (List<Transaction> transactionList) {
        ObservableList<Transaction> observableList = FXCollections.observableArrayList(transactionList);
        id.setCellValueFactory(new PropertyValueFactory<>("row"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalQuantity.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));
        SupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        personType.setCellValueFactory(new PropertyValueFactory<>("personType"));
        nationalId.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        family.setCellValueFactory(new PropertyValueFactory<>("family"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionQuantity.setCellValueFactory(new PropertyValueFactory<>("transactionQuantity"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        transactionTableView.setItems(observableList);

    }
    private void resetForm()
    {
        try {
            fillTransactionTable(TransactionService.findAll());
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
}

