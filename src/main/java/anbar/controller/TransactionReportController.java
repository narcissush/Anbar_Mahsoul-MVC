package anbar.controller;

import anbar.model.entity.*;
import anbar.model.entity.enums.*;
import anbar.model.service.TransactionService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TransactionReportController implements Initializable {

    @FXML
    private ComboBox<Brand> productBrandCmb;

    @FXML
    private ComboBox<TransactionType> transactionTypeCmb;

    @FXML
    private TextField userNameTxt, supplierNameTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private Button excelBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    private TableView<Transaction> transactionTableView;

    @FXML
    private TableColumn<Transaction, Integer> idCol;
    @FXML
    private TableColumn<Transaction, Integer> productIdCol;
    @FXML
    private TableColumn<Transaction, Category> categoryCol;
    @FXML
    private TableColumn<Transaction, Brand> brandCol;
    @FXML
    private TableColumn<Transaction, String> modelCol;
    @FXML
    private TableColumn<Transaction, Os> osCol;
    @FXML
    private TableColumn<Transaction, Boolean> hasChargerCol;
    @FXML
    private TableColumn<Transaction, Boolean> hasHeadsetCol;
    @FXML
    private TableColumn<Transaction, String> serialNumberCol;
    @FXML
    private TableColumn<Transaction, Integer> priceCol;
    @FXML
    private TableColumn<Transaction, Integer> totalQuantityCol;

    @FXML
    private TableColumn<Transaction, Integer> supplierIdCol;
    @FXML
    private TableColumn<Transaction, String> supplierNameCol;
    @FXML
    private TableColumn<Transaction, Person> personTypeCol;
    @FXML
    private TableColumn<Transaction, String> supplierNationalIdCol;
    @FXML
    private TableColumn<Transaction, String> postalcodeCol;
    @FXML
    private TableColumn<Transaction, String> phoneNumberCol;
    @FXML
    private TableColumn<Transaction, String> mobileNumberCol;

    @FXML
    private TableColumn<Transaction, Integer> userIdCol;
    @FXML
    private TableColumn<Transaction, String> userNationalIdCol;
    @FXML
    private TableColumn<Transaction, String> userNameCol;
    @FXML
    private TableColumn<Transaction, String> userFamilyCol;
    @FXML
    private TableColumn<Transaction, Gender> userGenderCol;
    @FXML
    private TableColumn<Transaction, LocalDate> userBirthDateCol;
    @FXML
    private TableColumn<Transaction, String> usernameCol;
    @FXML
    private TableColumn<Transaction, String> passwordCol;

    @FXML
    private TableColumn<Transaction, TransactionType> transactionTypeCol;
    @FXML
    private TableColumn<Transaction, Integer> transactionQuantityCol;
    @FXML
    private TableColumn<Transaction, LocalDateTime> transactionDateCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();


//Search-Btn-------------------------------------------------
        searchBtn.setOnAction(event -> {
            List<Transaction> transactionList = new ArrayList<>();

                Brand selectedBrand = productBrandCmb.getSelectionModel().getSelectedItem() != null
                        ? Brand.valueOf(productBrandCmb.getSelectionModel().getSelectedItem().toString())
                        : null;

            TransactionType selectedType = transactionTypeCmb.getSelectionModel().getSelectedItem()!=null
                    ? TransactionType.valueOf(transactionTypeCmb.getSelectionModel().getSelectedItem().toString())
                    : null;

                String selectedUserName = userNameTxt.getText()!=null
                        ? userNameTxt.getText()
                        : null;

                String selectedSupplierName = supplierNameTxt.getText() !=null
                        ? supplierNameTxt.getText()
                        : null;

            try {
                fillTransactionTable(TransactionService.findByFilters(selectedBrand, selectedType, selectedUserName, selectedSupplierName));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


        cancleBtn.setOnAction(event -> {
            resetForm();
        });
    }

    //fillTable--------------------------------------------------------------
    private void fillTransactionTable(List<Transaction> transaction) {
        ObservableList<Transaction> observableList = FXCollections.observableArrayList(transaction);

        idCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()).asObject());

        productIdCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getProduct().getId()).asObject());
        categoryCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getProduct().getCategory()));
        brandCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getProduct().getBrand()));
        modelCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProduct().getModel()));
        osCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getProduct().getOs()));
        hasChargerCol.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getProduct().isHasCharger()).asObject());
        hasHeadsetCol.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getProduct().isHasHeadset()).asObject());
        serialNumberCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProduct().getSerialNumber()));
        priceCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getProduct().getPrice()).asObject());
        totalQuantityCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getProduct().getTotalQuantity()).asObject());

        supplierIdCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getSupplier().getId()).asObject());
        supplierNameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getSupplierName()));
        personTypeCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getSupplier().getPersonType()));
        supplierNationalIdCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getNationalId()));
        postalcodeCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getPostalCode()));
        phoneNumberCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getPhoneNumber()));
        mobileNumberCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getMobileNumber()));

        userIdCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getUser().getId()).asObject());
        userNationalIdCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getNationalId()));
        userNameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getName()));
        userFamilyCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getFamily()));
        userGenderCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getUser().getGender()));
        userBirthDateCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getUser().getBirthDate()));
        usernameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getUsername()));
        passwordCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getPassword()));

        transactionTypeCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTransactionType()));
        transactionQuantityCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getTransactionQuantity()).asObject());
        transactionDateCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTransactionDate()));

        transactionTableView.setItems(observableList);

    }

    private void resetForm() {
        productBrandCmb.getItems().clear();
        transactionTypeCmb.getItems().clear();
        productBrandCmb.getItems().addAll(Brand.values());
        transactionTypeCmb.getItems().addAll(TransactionType.values());
        supplierNameTxt.clear();
        userNameTxt.clear();

        try {
            fillTransactionTable(TransactionService.findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
}