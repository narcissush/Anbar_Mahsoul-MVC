package anbar.controller;

import anbar.model.entity.Supplier;
import anbar.model.entity.enums.*;
import anbar.model.service.SupplierService;
import com.google.gson.JsonArray;
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resetSupplierForm();
        AtomicInteger i = new AtomicInteger();


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



        supplierDeleteBtn.setOnAction(event -> {
            try {
                SupplierService.delete(Integer.parseInt(supplierIdTxt.getText()));
                resetSupplierForm();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });



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



        supplierRefreshImg.setOnMouseClicked(event -> {
            resetSupplierForm();
        });
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
