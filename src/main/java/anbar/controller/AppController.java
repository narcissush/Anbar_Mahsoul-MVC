package anbar.controller;

import anbar.model.entity.Product;
import anbar.model.entity.Supplier;

import anbar.model.entity.enums.*;
import anbar.model.service.ProductService;
import anbar.model.service.SupplierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AppController implements Initializable {
    // فیلدهای  تامین کننده
    @FXML
    private TextField supplierIdTxt;
    @FXML
    private RadioButton naturalPersonRdo;
    @FXML
    private RadioButton legalPersonRdo;
    @FXML
    private RadioButton buyerRdo;
    @FXML
    private RadioButton sellerRdo;
    @FXML
    private ToggleGroup personToggle;
    @FXML
    private ToggleGroup partyToggle;
    @FXML
    private TextField supplierNameTxt;
    @FXML
    private TextField supplierNationalIdTxt;
    @FXML
    private TextField supplierPostalCodeTxt;
    @FXML
    private TextField supplierPhoneNumberTxt;
    @FXML
    private TextField supplierMobileTxt;
    @FXML
    private Button supplierSaveBtn;
    @FXML
    private Button supplierNewBtn;
    @FXML
    private Button supplierEditBtn;
    @FXML
    private Button supplierDeleteBtn;
    @FXML
    private Button supplierReportsBtn;


    //جستجو تامین کننده
    @FXML
    private ComboBox supplierSearchItemCmb;
    @FXML
    private TextField supplierSearchItem1Txt;
    @FXML
    private Button supplierSearchBtn;
    @FXML
    private ImageView supplierRefreshImg;
    @FXML
    private ComboBox<Enum<?>> supplierSearchByCmb;

    // جدول تامین کننده
    @FXML
    private TableView<Supplier> supplierTable;
    @FXML
    private TableColumn<Supplier, Integer> supplierIdCol;
    @FXML
    private TableColumn<Supplier, String> supplierNameCol;
    @FXML
    private TableColumn<Supplier, String> supplierNationalIdCol;
    @FXML
    private TableColumn<Supplier, String> supplierPostalCodeCol;
    @FXML
    private TableColumn<Supplier, String> supplierPhoneNumberCol;
    @FXML
    private TableColumn<Supplier, String> supplierMobileCol;
    @FXML
    private TableColumn<Supplier, Person> supplierPersonCol;
    @FXML
    private TableColumn<Supplier, Party> supplierPartyCol;


    //فیلدهای محصولات
    @FXML
    private TextField productIdTxt;
    @FXML
    private ComboBox<Category> productCategoryCmb;
    @FXML
    private ComboBox<Brand> productBrandCmb;
    @FXML
    private TextField productModelTxt;
    @FXML
    private ComboBox<Os> productOsCmb;
    @FXML
    private CheckBox headsetChk;
    @FXML
    private CheckBox chargerChk;
    @FXML
    private TextField productSerialTxt;
    @FXML
    private TextField productPriceTxt;
    @FXML
    private TextField productCountTxt;
    @FXML
    private Button productSaveBtn;
    @FXML
    private Button productNewBtn;
    @FXML
    private Button productEdiBtn;
    @FXML
    private Button inBoundBtn;
    @FXML
    private Button outBoundBtn;
    @FXML
    private Button productDeleteBtn;
    @FXML
    private Button productReportBtn;


    // جدول محصولات
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productCategoryCol;
    @FXML
    private TableColumn<Product, String> productModelCol;
    @FXML
    private TableColumn<Product, Brand> productBrandCol;
    @FXML
    private TableColumn<Product, Os> productOsCol;
    @FXML
    private TableColumn<Product, String> productSerialCol;
    @FXML
    private TableColumn<Product, Boolean> hasChargerCol;
    @FXML
    private TableColumn<Product, Boolean> hasHeadsetCol;
    @FXML
    private TableColumn<Product, Integer> productPriceCol;
    @FXML
    private TableColumn<Product, Integer> productCountCol;

    //جستجو محصولات
    @FXML
    private ComboBox productSearchItemCmb;
    @FXML
    private TextField productItem1Txt;
    @FXML
    private TextField productItem2Txt;
    @FXML
    private Button productSearchBtn;
    @FXML
    private ImageView productRefreshImg;
    @FXML
    private ComboBox<Enum<?>> productSearchByCmb;


    // اطلاعات کاربر
    @FXML
    private TextField userIdTxt;
    @FXML
    private TextField userNationalIdTxt;
    @FXML
    private TextField userFirstNameTxt;
    @FXML
    private TextField userFamilyTxt;
    @FXML
    private RadioButton userFamaleRdo;
    @FXML
    private RadioButton userMaleRdo;
    @FXML
    private ToggleGroup genderToggle;
    @FXML
    private DatePicker userBirthDate;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button userEditBtn;
    @FXML
    private ImageView userImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Product Controller
        AtomicInteger i = new AtomicInteger();
        productBrandCmb.getItems().addAll(Brand.values());
        productCategoryCmb.getItems().addAll(Category.values());
        productOsCmb.getItems().addAll(Os.values());
        productSearchItemCmb.getItems().addAll(ProductSearchList.values());
        resetProductForm();

        //Supplier Controller
        resetSupplierForm();


//New----------------------------------------------------------
        productNewBtn.setOnAction(event -> {
            resetProductForm();
            productCategoryCmb.setDisable(false);
            productBrandCmb.setDisable(false);
            productModelTxt.setDisable(false);
            productOsCmb.setDisable(false);
            headsetChk.setDisable(false);
            chargerChk.setDisable(false);
            productSerialTxt.setDisable(false);
            productPriceTxt.setDisable(false);
            productCountTxt.setDisable(false);
            productSaveBtn.setDisable(false);

        });
        supplierNewBtn.setOnAction(event -> {
            resetSupplierForm();
            naturalPersonRdo.setDisable(false);
            legalPersonRdo.setDisable(false);
            buyerRdo.setDisable(false);
            sellerRdo.setDisable(false);
            supplierNameTxt.setDisable(false);
            supplierNationalIdTxt.setDisable(false);
            supplierPostalCodeTxt.setDisable(false);
            supplierPhoneNumberTxt.setDisable(false);
            supplierMobileTxt.setDisable(false);
        });


//Edit-------------------------------

        productEdiBtn.setOnAction(event -> {
            productCategoryCmb.setDisable(false);
            productBrandCmb.setDisable(false);
            productModelTxt.setDisable(false);
            productOsCmb.setDisable(false);
            headsetChk.setDisable(false);
            chargerChk.setDisable(false);
            productSerialTxt.setDisable(false);
            productPriceTxt.setDisable(false);
            productCountTxt.setDisable(false);
            productSaveBtn.setDisable(false);

        });
        supplierEditBtn.setOnAction(event -> {
            //supplierIdTxt.setDisable(false);
            naturalPersonRdo.setDisable(false);
            legalPersonRdo.setDisable(false);
            buyerRdo.setDisable(false);
            sellerRdo.setDisable(false);
            supplierNameTxt.setDisable(false);
            supplierNationalIdTxt.setDisable(false);
            supplierPostalCodeTxt.setDisable(false);
            supplierPhoneNumberTxt.setDisable(false);
            supplierMobileTxt.setDisable(false);
        });

//Save-------------------------------------------------------------------
        productSaveBtn.setOnAction(event -> {
            try {
                if (productIdTxt.getText().isEmpty()) {
                    Product product =
                            Product.builder()
                                    //.id(Integer.parseInt(productIdTxt.getText()))
                                    .category(productCategoryCmb.getSelectionModel().getSelectedItem())
                                    .brand(productBrandCmb.getSelectionModel().getSelectedItem())
                                    .model(productModelTxt.getText())
                                    .os(productOsCmb.getSelectionModel().getSelectedItem())
                                    .hasHeadset(headsetChk.isSelected())
                                    .hasCharger(chargerChk.isSelected())
                                    .serialNumber(productSerialTxt.getText())
                                    .price(Integer.parseInt(productPriceTxt.getText()))
                                    .count(Integer.parseInt(productCountTxt.getText()))
                                    .build();

                    ProductService.save(product);
                    resetProductForm();
                } else {
                    Product product =
                            Product.builder()
                                    .id(Integer.parseInt(productIdTxt.getText()))
                                    .category(productCategoryCmb.getSelectionModel().getSelectedItem())
                                    .brand(productBrandCmb.getSelectionModel().getSelectedItem())
                                    .model(productModelTxt.getText())
                                    .os(productOsCmb.getSelectionModel().getSelectedItem())
                                    .hasHeadset(headsetChk.isSelected())
                                    .hasCharger(chargerChk.isSelected())
                                    .serialNumber(productSerialTxt.getText())
                                    .price(Integer.parseInt(productPriceTxt.getText()))
                                    .count(Integer.parseInt(productCountTxt.getText()))
                                    .build();
                    ProductService.edit(product);
                    resetProductForm();
                }
                new Alert(Alert.AlertType.INFORMATION, "Product Saved", ButtonType.OK).show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });


        supplierSaveBtn.setOnAction(event -> {
            RadioButton selectedPersonRdo = (RadioButton) personToggle.getSelectedToggle();
            RadioButton selectedPartyRdo = (RadioButton) partyToggle.getSelectedToggle();
            try {
                if (supplierIdTxt.getText().isEmpty()) {
                    Supplier supplier =
                            Supplier.builder()
                                    //.id(Integer.parseInt(supplierIdTxt.getText()))
                                    .personType(Person.valueOf(selectedPersonRdo.getText()))
                                    .partyType(Party.valueOf(selectedPartyRdo.getText()))
                                    .name(supplierNameTxt.getText())
                                    .nationalId(supplierNationalIdTxt.getText())
                                    .postalCode(supplierPostalCodeTxt.getText())
                                    .phoneNumber(supplierPhoneNumberTxt.getText())
                                    .mobileNumber(supplierMobileTxt.getText())
                                    .build();
                    SupplierService.save(supplier);

                } else {
                    Supplier supplier =
                            Supplier.builder()
                                    .id(Integer.parseInt(supplierIdTxt.getText()))
                                    .personType(Person.valueOf(selectedPersonRdo.getText()))
                                    .partyType(Party.valueOf(selectedPartyRdo.getText()))
                                    .name(supplierNameTxt.getText())
                                    .nationalId(supplierNationalIdTxt.getText())
                                    .postalCode(supplierPostalCodeTxt.getText())
                                    .phoneNumber(supplierPhoneNumberTxt.getText())
                                    .mobileNumber(supplierMobileTxt.getText())
                                    .build();
                    SupplierService.edit(supplier);
                }

                new Alert(Alert.AlertType.INFORMATION, "Supplier Saved", ButtonType.OK).show();
                resetSupplierForm();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });


//Delete----------------------------------------------------------------------
        productDeleteBtn.setOnAction(event -> {
            try {
                ProductService.delete(Integer.parseInt(productIdTxt.getText()));
                resetProductForm();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        supplierDeleteBtn.setOnAction(event -> {
            try {
                SupplierService.delete(Integer.parseInt(supplierIdTxt.getText()));
                resetSupplierForm();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


//EventHandler-------------------------------------------------------------

        EventHandler<Event> tableChangeEvent1 = (mouseEvent) -> {
            resetProductForm();
            Product selected = productsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                productIdTxt.setText(String.valueOf(selected.getId()));
                productCategoryCmb.getSelectionModel().select(selected.getCategory());
                productBrandCmb.getSelectionModel().select(selected.getBrand());
                productModelTxt.setText(selected.getModel());
                productOsCmb.getSelectionModel().select(selected.getOs());
                headsetChk.setSelected(selected.isHasHeadset());
                chargerChk.setSelected(selected.isHasCharger());
                productSerialTxt.setText(selected.getSerialNumber());
                productPriceTxt.setText(String.valueOf(selected.getPrice()));
                productCountTxt.setText(String.valueOf(selected.getCount()));
            }

        };
        productsTable.setOnMouseReleased(tableChangeEvent1);
        productsTable.setOnKeyReleased(tableChangeEvent1);


        EventHandler<Event> tableChangeEvent2 = (mouseEvent) -> {
            resetSupplierForm();
            Supplier selected = supplierTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                supplierIdTxt.setText(String.valueOf(selected.getId()));
                supplierNameTxt.setText(selected.getName());
                supplierNationalIdTxt.setText(selected.getNationalId());
                supplierPostalCodeTxt.setText(selected.getPostalCode());
                supplierPhoneNumberTxt.setText(selected.getPhoneNumber());
                supplierMobileTxt.setText(selected.getMobileNumber());
                if (selected.getPersonType() == Person.حقوقی) naturalPersonRdo.setSelected(true);
                else legalPersonRdo.setSelected(true);
                if (selected.getPartyType() == Party.فروشنده) buyerRdo.setSelected(true);
                else sellerRdo.setSelected(true);
            }
        };
        supplierTable.setOnMouseReleased(tableChangeEvent2);
        supplierTable.setOnKeyReleased(tableChangeEvent2);


//productSearchItem------------------------------------------------------

        productSearchItemCmb.setOnAction(event -> {

            if ("findByCategory".equals(productSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                productSearchByCmb.getItems().clear();
                productItem1Txt.setVisible(false);
                productItem2Txt.setVisible(false);
                productSearchByCmb.getItems().addAll(Category.values());
                productSearchByCmb.setVisible(true);
                i.set(1);

            } else if ("findByBrand".equals(productSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                productSearchByCmb.getItems().clear();
                productItem1Txt.setVisible(false);
                productItem2Txt.setVisible(false);
                productSearchByCmb.getItems().addAll(Brand.values());
                productSearchByCmb.setVisible(true);
                i.set(2);
            } else if ("findByPrice".equals(productSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                productSearchByCmb.getItems().clear();
                productSearchByCmb.setVisible(false);
                productItem1Txt.setVisible(true);
                productItem2Txt.setVisible(true);
                i.set(3);
            }
        });


        supplierSearchItemCmb.setOnAction(event -> {

            if ("findByNationalId".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(false);
                supplierSearchItem1Txt.setVisible(true);
                i.set(1);

            } else if ("findByName".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(false);
                supplierSearchItem1Txt.setVisible(true);
                i.set(2);
            } else if ("findByPerson".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(false);
                supplierSearchByCmb.getItems().addAll(Person.values());
                supplierSearchItem1Txt.setVisible(true);
                i.set(3);
            } else if ("findByParty".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(false);
                supplierSearchByCmb.getItems().addAll(Party.values());
                supplierSearchItem1Txt.setVisible(true);
                i.set(4);
            }
        });


//Search-Btn-------------------------------------------------

        productSearchBtn.setOnAction(event -> {
            List<Product> productList = new ArrayList<>();
            try {
                if (i.get() == 1) {
                    productList = ProductService.findByCategory(productSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillProductTable(productList);
                } else if (i.get() == 2) {
                    productList = ProductService.findByBrand(productSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillProductTable(productList);
                } else if (i.get() == 3) {
                    productList = ProductService.findByPrice(Integer.parseInt(productItem1Txt.getText()), Integer.parseInt(productItem2Txt.getText()));
                    fillProductTable(productList);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


        supplierSearchBtn.setOnAction(event -> {
            List<Supplier> supplierList = new ArrayList<>();
            try {
                if (i.get() == 1) {
                    supplierList = SupplierService.findByNationalId(supplierSearchItem1Txt.getText());
                    fillSupplierTable(supplierList);

                } else if (i.get() == 2) {

                    supplierList = SupplierService.findByName(supplierSearchItem1Txt.getText());
                    fillSupplierTable(supplierList);

                } else if (i.get() == 3) {
                    supplierList = SupplierService.findByPerson(supplierSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillSupplierTable(supplierList);
                } else if (i.get() == 4) {
                    supplierList = SupplierService.findByParty(supplierSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillSupplierTable(supplierList);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

//RefreshImg-----------------------------------
        productRefreshImg.setOnMouseClicked(event -> {
            resetProductForm();
        });

        supplierRefreshImg.setOnMouseClicked(event -> {
            resetSupplierForm();
        });
    }


//fillTable--------------------------------------------------------------
    private void fillProductTable(List<Product> productList) {
        ObservableList<Product> observableList = FXCollections.observableArrayList(productList);
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        productModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        productBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        productOsCol.setCellValueFactory(new PropertyValueFactory<>("os"));
        hasChargerCol.setCellValueFactory(new PropertyValueFactory<>("hasCharger"));
        hasHeadsetCol.setCellValueFactory(new PropertyValueFactory<>("hasHeadset"));
        productSerialCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        productsTable.setItems(observableList);
    }

    private void fillSupplierTable(List<Supplier> supplierList) {

        ObservableList<Supplier> observableList = FXCollections.observableArrayList(supplierList);
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        supplierPersonCol.setCellValueFactory(new PropertyValueFactory<>("personType"));
        supplierPartyCol.setCellValueFactory(new PropertyValueFactory<>("partyType"));
        supplierNationalIdCol.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        supplierPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        supplierPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        supplierMobileCol.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        supplierNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierTable.setItems(observableList);
    }

//Reset Form------------------------------------------
    private void resetProductForm() {
        productCategoryCmb.getSelectionModel().clearSelection();
        productBrandCmb.getSelectionModel().clearSelection();
        productModelTxt.clear();
        productOsCmb.getSelectionModel().clearSelection();
        headsetChk.setSelected(false);
        chargerChk.setSelected(false);
        productSerialTxt.clear();
        productPriceTxt.clear();
        productCountTxt.clear();
        productIdTxt.clear();
        productCategoryCmb.getSelectionModel().clearSelection();
        productBrandCmb.getSelectionModel().clearSelection();
        productModelTxt.clear();
        productOsCmb.getSelectionModel().clearSelection();
        headsetChk.setSelected(false);
        chargerChk.setSelected(false);
        productSerialTxt.clear();
        productPriceTxt.clear();
        productCountTxt.clear();
        try {
            fillProductTable(ProductService.findAll());
        } catch (Exception e) {
            System.out.println("error");
        }
        productIdTxt.setDisable(true);
        productCategoryCmb.setDisable(true);
        productBrandCmb.setDisable(true);
        productModelTxt.setDisable(true);
        productOsCmb.setDisable(true);
        headsetChk.setDisable(true);
        chargerChk.setDisable(true);
        productSerialTxt.setDisable(true);
        productPriceTxt.setDisable(true);
        productCountTxt.setDisable(true);
        productSaveBtn.setDisable(true);
        productItem1Txt.setVisible(false);
        productItem2Txt.setVisible(false);
        productSearchByCmb.setVisible(false);

    }

    private void resetSupplierForm() {
        supplierIdTxt.clear();
        naturalPersonRdo.isSelected();
        legalPersonRdo.isSelected();
        buyerRdo.isSelected();
        sellerRdo.isSelected();
        supplierNameTxt.clear();
        supplierNationalIdTxt.clear();
        supplierPostalCodeTxt.clear();
        supplierPhoneNumberTxt.clear();
        supplierMobileTxt.clear();
        try {
            fillSupplierTable(SupplierService.findAll());
        } catch (Exception e) {
            System.out.println("error");
        }
        supplierIdTxt.setDisable(true);
        naturalPersonRdo.setDisable(true);
        legalPersonRdo.setDisable(true);
        buyerRdo.setDisable(true);
        sellerRdo.setDisable(true);
        supplierNameTxt.setDisable(true);
        supplierNationalIdTxt.setDisable(true);
        supplierPostalCodeTxt.setDisable(true);
        supplierPhoneNumberTxt.setDisable(true);
        supplierMobileTxt.setDisable(true);
        supplierSearchItem1Txt.setVisible(false);
        supplierSearchByCmb.setVisible(false);

    }


}
