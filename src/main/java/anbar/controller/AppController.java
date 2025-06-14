package anbar.controller;

import anbar.model.entity.Product;
import anbar.model.entity.Supplier;

import anbar.model.entity.enums.*;
import anbar.model.service.ProductService;
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
    private ToggleGroup partyToggle;
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
    private Label supplierSearchItem1Lbl;
    @FXML
    private TextField supplierSearchItem1Txt;
    @FXML
    private Label supplierSearchItem2Lbl;
    @FXML
    private TextField supplierSearchItem2Txt;
    @FXML
    private Button supplierSearchBtn;
    @FXML
    private ImageView supplierRefreshImg;

    // جدول تامین کننده
    @FXML
    private TableView<Supplier> supplierTable;
    @FXML
    private TableColumn<Supplier, Integer> supplierIdCol;
    @FXML
    private TableColumn<Supplier, Person> supplierPersonCol;
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
        AtomicInteger i = new AtomicInteger();
        productBrandCmb.getItems().addAll(Brand.values());
        productCategoryCmb.getItems().addAll(Category.values());
        productOsCmb.getItems().addAll(Os.values());
        productSearchItemCmb.getItems().addAll(ProductSearchList.values());
        resetProductForm();

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
                } else  {
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
                }

                new Alert(Alert.AlertType.INFORMATION, "Product Saved", ButtonType.OK).show();
                resetProductForm();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });
        productDeleteBtn.setOnAction(event -> {
            try {
                ProductService.delete(Integer.parseInt(productIdTxt.getText()));
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


        EventHandler<Event> tableChangeEvent = (mouseEvent) -> {
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
        productsTable.setOnMouseReleased(tableChangeEvent);
        productsTable.setOnKeyReleased(tableChangeEvent);


        productSearchItemCmb.setOnAction(event -> {

            if ("findByCategory".equals(productSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                productSearchByCmb.getItems().clear();
                productItem1Txt.setVisible(false);
                productItem2Txt.setVisible(false);
                productSearchByCmb.getItems().addAll(Category.values());
                productSearchByCmb.setVisible(true);
                i.set(1);

            }else if ("findByBrand".equals(productSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                productSearchByCmb.getItems().clear();
                productItem1Txt.setVisible(false);
                productItem2Txt.setVisible(false);
                productSearchByCmb.getItems().addAll(Brand.values());
                productSearchByCmb.setVisible(true);
                i.set(2);
            }
            else if ("findByPrice".equals(productSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                productSearchByCmb.getItems().clear();
                productSearchByCmb.setVisible(false);
                productItem1Txt.setVisible(true);
                productItem2Txt.setVisible(true);
                i.set(3);
            }
        });

        productSearchBtn.setOnAction(event -> {
            List<Product> productList=new ArrayList<>();
            try {
                if (i.get() ==1) {
                    productList = ProductService.findByCategory(productSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillProductTable(productList);
                } else if (i.get() ==2) {
                    productList = ProductService.findByBrand(productSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillProductTable(productList);
                }else if(i.get() ==3){
                    productList = ProductService.findByPrice(Integer.parseInt(productItem1Txt.getText()),Integer.parseInt(productItem2Txt.getText()));
                    fillProductTable(productList);
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        productRefreshImg.setOnMouseClicked(event -> {
            resetProductForm();
        });

         }

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


}
