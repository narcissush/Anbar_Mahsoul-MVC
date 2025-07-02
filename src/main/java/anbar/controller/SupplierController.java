package anbar.controller;

import anbar.FormManager;
import anbar.model.entity.Supplier;
import anbar.model.entity.enums.*;
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

public class SupplierController implements Initializable {
    @FXML
    private RadioButton naturalPersonRdo, legalPersonRdo;

    @FXML
    private ToggleGroup personToggle;

    @FXML
    private TextField supplierIdTxt, supplierNameTxt, supplierNationalIdTxt, supplierPostalCodeTxt, supplierPhoneNumberTxt, supplierMobileTxt;

    @FXML
    private Button supplierSaveBtn, supplierNewBtn, supplierEditBtn, supplierDeleteBtn, supplierReportsBtn, selectSupplierBtn;

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
    private TableColumn<Supplier, Integer> supplierPersonCol;
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

    int i;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resetSupplierForm();
        supplierSearchItemCmb.getItems().addAll(supplierSearchList.values());
        supplierNewBtn.setOnAction(event -> {
            resetSupplierForm();
            naturalPersonRdo.setDisable(false);
            legalPersonRdo.setDisable(false);
            supplierNameTxt.setDisable(false);
            supplierNationalIdTxt.setDisable(false);
            supplierPostalCodeTxt.setDisable(false);
            supplierPhoneNumberTxt.setDisable(false);
            supplierMobileTxt.setDisable(false);
        });

        selectSupplierBtn.setOnAction(event -> {
            AppState.supplier =
                    Supplier.builder()
                            .id(Integer.parseInt(supplierIdTxt.getText()))
                            .personType(Person.valueOf(((RadioButton) personToggle.getSelectedToggle()).getText()))
                            .supplierName(supplierNameTxt.getText())
                            .nationalId(supplierNationalIdTxt.getText())
                            .postalCode(supplierPostalCodeTxt.getText())
                            .phoneNumber(supplierPhoneNumberTxt.getText())
                            .mobileNumber(supplierMobileTxt.getText())
                            .build();
            FormManager.mainFormController.setSupplier();
        });

        supplierEditBtn.setOnAction(event -> {
            //supplierIdTxt.setDisable(false);
            //naturalPersonRdo.setDisable(false);
            //legalPersonRdo.setDisable(false);
            //supplierNameTxt.setDisable(false);
            //supplierNationalIdTxt.setDisable(false);
            supplierPostalCodeTxt.setDisable(false);
            supplierPhoneNumberTxt.setDisable(false);
            supplierMobileTxt.setDisable(false);
        });

        supplierSaveBtn.setOnAction(event -> {
            RadioButton selectedPersonRdo = (RadioButton) personToggle.getSelectedToggle();
            try {
                if (supplierIdTxt.getText().isEmpty()) {
                    Supplier supplier =
                            Supplier.builder()
                                    //.id(Integer.parseInt(supplierIdTxt.getText()))
                                    .personType(Person.valueOf(selectedPersonRdo.getText()))
                                    .supplierName(supplierNameTxt.getText())
                                    .nationalId(supplierNationalIdTxt.getText())
                                    .postalCode(supplierPostalCodeTxt.getText())
                                    .phoneNumber(supplierPhoneNumberTxt.getText())
                                    .mobileNumber(supplierMobileTxt.getText())
                                    .build();
                    SupplierService.save(supplier);
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved", ButtonType.OK).show();
                    resetSupplierForm();

                } else {
                    Supplier supplier =
                            Supplier.builder()
                                    .id(Integer.parseInt(supplierIdTxt.getText()))
                                    .personType(Person.valueOf(selectedPersonRdo.getText()))
                                    .supplierName(supplierNameTxt.getText())
                                    .nationalId(supplierNationalIdTxt.getText())
                                    .postalCode(supplierPostalCodeTxt.getText())
                                    .phoneNumber(supplierPhoneNumberTxt.getText())
                                    .mobileNumber(supplierMobileTxt.getText())
                                    .build();
                    SupplierService.edit(supplier);
                    new Alert(Alert.AlertType.INFORMATION, "Supplier edited", ButtonType.OK).show();
                    resetSupplierForm();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


        supplierDeleteBtn.setOnAction(event -> {
            try {
                SupplierService.delete(Integer.parseInt(supplierIdTxt.getText()));
                resetSupplierForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


        EventHandler<Event> tableChangeEvent = (mouseEvent) -> {
            resetSupplierForm();
            Supplier selected = supplierTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                supplierIdTxt.setText(String.valueOf(selected.getId()));
                supplierNameTxt.setText(selected.getSupplierName());
                supplierNationalIdTxt.setText(selected.getNationalId());
                supplierPostalCodeTxt.setText(selected.getPostalCode());
                supplierPhoneNumberTxt.setText(selected.getPhoneNumber());
                supplierMobileTxt.setText(selected.getMobileNumber());
                if (selected.getPersonType() == Person.حقوقی) naturalPersonRdo.setSelected(true);
                else legalPersonRdo.setSelected(true);

            }
        };
        supplierTable.setOnMouseReleased(tableChangeEvent);
        supplierTable.setOnKeyReleased(tableChangeEvent);


        supplierSearchItemCmb.setOnAction(event -> {

            if ("کدملی".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(false);
                supplierSearchItem1Txt.setVisible(true);
                i = 1;

            } else if ("نام".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(false);
                supplierSearchItem1Txt.setVisible(true);
                i = 2;
            } else if ("شخص".equals(supplierSearchItemCmb.getSelectionModel().getSelectedItem().toString())) {
                supplierSearchByCmb.getItems().clear();
                supplierSearchByCmb.setVisible(true);
                supplierSearchItem1Txt.setVisible(false);
                supplierSearchByCmb.getItems().addAll(Person.values());
                i = 3;
            }
        });


        supplierSearchBtn.setOnAction(event -> {
            List<Supplier> supplierList = new ArrayList<>();
            try {
                if (i == 1) {
                    supplierList = SupplierService.findByNationalId(supplierSearchItem1Txt.getText());
                    fillSupplierTable(supplierList);

                } else if (i == 2) {

                    supplierList = SupplierService.findByName(supplierSearchItem1Txt.getText());
                    fillSupplierTable(supplierList);

                } else if (i == 3) {
                    supplierList = SupplierService.findByPerson(supplierSearchByCmb.getSelectionModel().getSelectedItem().toString());
                    fillSupplierTable(supplierList);
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        supplierRefreshImg.setOnMouseClicked(event -> {
            resetSupplierForm();
        });
    }

    private void fillSupplierTable(List<Supplier> supplierList) {
        ObservableList<Supplier> observableList = FXCollections.observableArrayList(supplierList);
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        supplierPersonCol.setCellValueFactory(new PropertyValueFactory<>("personType"));
        supplierNationalIdCol.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        supplierPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        supplierPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        supplierMobileCol.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        supplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierTable.setItems(observableList);
    }

    private void resetSupplierForm() {
        supplierIdTxt.clear();
        naturalPersonRdo.isSelected();
        legalPersonRdo.isSelected();
        supplierNameTxt.clear();
        supplierNationalIdTxt.clear();
        supplierPostalCodeTxt.clear();
        supplierPhoneNumberTxt.clear();
        supplierMobileTxt.clear();
        try {
            fillSupplierTable(SupplierService.findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        supplierIdTxt.setDisable(true);
        naturalPersonRdo.setDisable(true);
        legalPersonRdo.setDisable(true);
        supplierNameTxt.setDisable(true);
        supplierNationalIdTxt.setDisable(true);
        supplierPostalCodeTxt.setDisable(true);
        supplierPhoneNumberTxt.setDisable(true);
        supplierMobileTxt.setDisable(true);
        supplierSearchItem1Txt.setVisible(false);
        supplierSearchByCmb.setVisible(false);

    }


}
