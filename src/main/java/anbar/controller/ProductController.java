package anbar.controller;

import anbar.model.entity.Product;
import anbar.model.entity.enums.*;

import anbar.model.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductController implements Initializable {

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
    private TextField productQuantityTxt;
    @FXML
    private Button productSaveBtn;
    @FXML
    private Button productNewBtn;
    @FXML
    private Button productEdiBtn;
    @FXML
    private Button transactionBtn;

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
    private TableColumn<Product, Integer> productQuantityCol;

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
            productQuantityTxt.setDisable(false);
            productSaveBtn.setDisable(false);

        });


//Edit-------------------------------

        productEdiBtn.setOnAction(event -> {
            //productCategoryCmb.setDisable(false);
            //productBrandCmb.setDisable(false);
            //productModelTxt.setDisable(false);
            //productOsCmb.setDisable(false);
            headsetChk.setDisable(false);
            chargerChk.setDisable(false);
            //productSerialTxt.setDisable(false);
            productPriceTxt.setDisable(false);
            //productQuantityTxt.setDisable(false);
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
                                    .quantity(Integer.parseInt(productQuantityTxt.getText()))
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
                                    .quantity(Integer.parseInt(productQuantityTxt.getText()))
                                    .build();
                    ProductService.edit(product);
                    resetProductForm();
                }
                new Alert(Alert.AlertType.INFORMATION, "Product Saved", ButtonType.OK).show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }

        });


//Delete----------------------------------------------------------------------
        productDeleteBtn.setOnAction(event -> {
            try {
                ProductService.delete(Integer.parseInt(productIdTxt.getText()));
                resetProductForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


//EventHandler-------------------------------------------------------------

        EventHandler<Event> tableChangeEvent = (mouseEvent -> {
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
                productQuantityTxt.setText(String.valueOf(selected.getQuantity()));
            }
        });



        productsTable.setOnMouseReleased(tableChangeEvent);
        productsTable.setOnKeyReleased(tableChangeEvent);


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
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


//RefreshImg-----------------------------------
        productRefreshImg.setOnMouseClicked(event -> {
            resetProductForm();
        });

        //Transaction-------------------------------
        transactionBtn.setOnAction(event -> {
            try {
                Stage secondStage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Transaction.fxml")));
                secondStage.setScene(scene);
                secondStage.setTitle("ورود کالا");
                secondStage.show();
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });



    }

        //fillTable--------------------------------------------------------------
        private void fillProductTable (List<Product> productList) {
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

            productQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            productsTable.setItems(observableList);

        }
        //Reset Form------------------------------------------
        private void resetProductForm () {
            productCategoryCmb.getSelectionModel().clearSelection();
            productBrandCmb.getSelectionModel().clearSelection();
            productModelTxt.clear();
            productOsCmb.getSelectionModel().clearSelection();
            headsetChk.setSelected(false);
            chargerChk.setSelected(false);
            productSerialTxt.clear();
            productPriceTxt.clear();
            productQuantityTxt.clear();
            productIdTxt.clear();
            productCategoryCmb.getSelectionModel().clearSelection();
            productBrandCmb.getSelectionModel().clearSelection();
            productModelTxt.clear();
            productOsCmb.getSelectionModel().clearSelection();
            headsetChk.setSelected(false);
            chargerChk.setSelected(false);
            productSerialTxt.clear();
            productPriceTxt.clear();
            productQuantityTxt.clear();
            try {
                fillProductTable(ProductService.findAll());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
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
            productQuantityTxt.setDisable(true);
            productSaveBtn.setDisable(true);
            productItem1Txt.setVisible(false);
            productItem2Txt.setVisible(false);
            productSearchByCmb.setVisible(false);

        }

    }




