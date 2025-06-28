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
    private TextField usernameTxt,supplierNameTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private Button excelBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    private TableView<Transaction> transactionTableView;

    @FXML private TableColumn<Transaction, Integer> id;
    @FXML private TableColumn<Transaction, Integer> productId;
    @FXML private TableColumn<Transaction, Category> category;
    @FXML private TableColumn<Transaction, Brand> brand;
    @FXML private TableColumn<Transaction, String> model;
    @FXML private TableColumn<Transaction, Os> os;
    @FXML private TableColumn<Transaction, Boolean> hasCharger;
    @FXML private TableColumn<Transaction, Boolean> hasHeadset;
    @FXML private TableColumn<Transaction, String> serialNumber;
    @FXML private TableColumn<Transaction, Integer> price;
    @FXML private TableColumn<Transaction, Integer> totalQuantity;

    @FXML private TableColumn<Transaction, Integer> supplierId;
    @FXML private TableColumn<Transaction, String> supplierName;
    @FXML private TableColumn<Transaction, Person> personType;
    @FXML private TableColumn<Transaction, String> supplierNationalId;
    @FXML private TableColumn<Transaction, String> postalcode;
    @FXML private TableColumn<Transaction, String> phoneNumber;
    @FXML private TableColumn<Transaction, String> mobileNumber;

    @FXML private TableColumn<Transaction, Integer> userId;
    @FXML private TableColumn<Transaction, String> userNationalId;
    @FXML private TableColumn<Transaction, String> userFirstName;
    @FXML private TableColumn<Transaction, String> userFamily;
    @FXML private TableColumn<Transaction, Gender> userGender;
    @FXML private TableColumn<Transaction, LocalDate> userBirthDate;
    @FXML private TableColumn<Transaction, String> userName;
    @FXML private TableColumn<Transaction, String> password;

    @FXML private TableColumn<Transaction, TransactionType> transactionType;
    @FXML private TableColumn<Transaction, Integer> transactionQuantity;
    @FXML private TableColumn<Transaction, LocalDateTime> transactionDate;

    Brand selectedBrand = null;
    TransactionType selectedType = null;
    String selectedUsername = null;
    String selectedSupplierName = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();




//Search-Btn-------------------------------------------------
        searchBtn.setOnAction(event -> {
            List<Transaction> transactionList = new ArrayList<>();
            try {
                selectedBrand= Brand.valueOf(productBrandCmb.getSelectionModel().getSelectedItem().toString());
                selectedType=TransactionType.valueOf(transactionTypeCmb.getSelectionModel().getSelectedItem().toString());
                selectedUsername=usernameTxt.getText();
                selectedSupplierName=supplierNameTxt.getText();
                fillTransactionTable(TransactionService.findByFilters(selectedBrand, selectedType, selectedUsername, selectedSupplierName));
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
    private void fillTransactionTable (List<Transaction> transaction) {
        ObservableList<Transaction> observableList = FXCollections.observableArrayList(transaction);

            id.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()).asObject());

            productId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getProduct().getId()).asObject());
            category.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getProduct().getCategory()));
            brand.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getProduct().getBrand()));
            model.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProduct().getModel()));
            os.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getProduct().getOs()));
            hasCharger.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getProduct().isHasCharger()).asObject());
            hasHeadset.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().getProduct().isHasHeadset()).asObject());
            serialNumber.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProduct().getSerialNumber()));
            price.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getProduct().getPrice()).asObject());
            totalQuantity.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getProduct().getTotalQuantity()).asObject());

            supplierId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getSupplier().getId()).asObject());
            supplierName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getSupplierName()));
            personType.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getSupplier().getPersonType()));
            supplierNationalId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getNationalId()));
            postalcode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getPostalCode()));
            phoneNumber.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getPhoneNumber()));
            mobileNumber.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSupplier().getMobileNumber()));

            userId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getUser().getId()).asObject());
            userNationalId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getNationalId()));
            userFirstName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getName()));
            userFamily.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getFamily()));
            userGender.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getUser().getGender()));
            userBirthDate.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getUser().getBirthDate()));
            userName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getUsername()));
            password.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUser().getPassword()));

            transactionType.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTransactionType()));
            transactionQuantity.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getTransactionQuantity()).asObject());
            transactionDate.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTransactionDate()));

        transactionTableView.setItems(observableList);

    }

    private void resetForm()
    {
        productBrandCmb.getItems().clear();
        transactionTypeCmb.getItems().clear();
        productBrandCmb.getItems().addAll(Brand.values());
        transactionTypeCmb.getItems().addAll(TransactionType.values());
        supplierNameTxt.clear();
        usernameTxt.clear();

        try {
            fillTransactionTable(TransactionService.findAll());
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
}