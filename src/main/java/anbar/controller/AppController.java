package anbar.controller;

import anbar.model.entity.Product;
import anbar.model.entity.Supplier;

import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Os;
import anbar.model.entity.enums.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    // فیلدهای  تامین کننده
    @FXML private TextField supplierIdTxt;
    @FXML private RadioButton naturalPersonRdo;
    @FXML private RadioButton legalPersonRdo;
    @FXML private RadioButton buyerRdo;
    @FXML private RadioButton sellerRdo;
    @FXML private TextField supplierNameTxt;
    @FXML private TextField supplierNationalIdTxt;
    @FXML private TextField supplierPostalCodeTxt;
    @FXML private TextField supplierPhoneNumberTxt;
    @FXML private TextField supplierMobileTxt;
    @FXML private ToggleGroup partyToggle;
    @FXML private Button supplierSaveBtn;
    @FXML private Button supplierNewBtn;
    @FXML private Button supplierEditBtn;
    @FXML private Button supplierDeleteBtn;
    @FXML private Button supplierReportsBtn;




    //جستجو تامین کننده
    @FXML private ComboBox supplierSearchItemCmb;
    @FXML private Label supplierSearchItem1Lbl;
    @FXML private TextField supplierSearchItem1Txt;
    @FXML private Label supplierSearchItem2Lbl;
    @FXML private TextField supplierSearchItem2Txt;
    @FXML private Button supplierSearchBtn;
    @FXML private ImageView supplierRefreshImg;

    // جدول تامین کننده
    @FXML private TableView<Supplier> supplierTable;
    @FXML private TableColumn<Supplier,Integer> supplierIdCol;
    @FXML private TableColumn<Supplier, Person> supplierPersonCol;
    @FXML private TableColumn<Supplier, String> supplierNameCol;
    @FXML private TableColumn<Supplier, String> supplierNationalIdCol;
    @FXML private TableColumn<Supplier, String> supplierPostalCodeCol;
    @FXML private TableColumn<Supplier, String> supplierPhoneNumberCol;
    @FXML private TableColumn<Supplier, String> supplierMobileCol;



    //فیلدهای محصولات
    @FXML private TextField productIdTxt;
    @FXML private ComboBox productGroupCmb;
    @FXML private ComboBox productBrandCmb;
    @FXML private ComboBox productModelCmb;
    @FXML private ComboBox productOsCmb;
    @FXML private CheckBox headsetChk;
    @FXML private CheckBox chargerChk;
    @FXML private TextField productSerialTxt;
    @FXML private TextField productPriceTxt;
    @FXML private TextField productCountTxt;
    @FXML private Button productSaveBtn;
    @FXML private Button productNewBtn;
    @FXML private Button productEdiBtn;
    @FXML private Button inBoundBtn;
    @FXML private Button outBoundBtn;
    @FXML private Button productDeleteBtn;
    @FXML private Button productReportBtn;



    // جدول محصولات
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productIdCol;
    @FXML private TableColumn<Product, String> productModelCol;
    @FXML private TableColumn<Product, Brand> productBrandCol;
    @FXML private TableColumn<Product, Os> productOsCol;
    @FXML private TableColumn<Product, String> productSerialCol;
    @FXML private TableColumn<Product, Boolean> hasChargerCol;
    @FXML private TableColumn<Product, Boolean> hasHeadsetCol;
    @FXML private TableColumn<Product,Integer> productPriceCol;

    //جستجو محصولات
    @FXML private ComboBox productSearchItemCmb;
    @FXML private Label productItem1Lbl;
    @FXML private TextField productItem1Txt;
    @FXML private Label productItem2Lbl;
    @FXML private TextField productItem2Txt;
    @FXML private Button productSearchBtn;
    @FXML private ImageView productRefreshImg;


    // اطلاعات کاربر
    @FXML private TextField userIdTxt;
    @FXML private TextField userNationalIdTxt;
    @FXML private TextField userFirstNameTxt;
    @FXML private TextField userFamilyTxt;
    @FXML private RadioButton userFamaleRdo;
    @FXML private RadioButton userMaleRdo;
    @FXML private ToggleGroup genderToggle;
    @FXML private DatePicker userBirthDate;
    @FXML private TextField usernameTxt;
    @FXML private PasswordField passwordTxt;
    @FXML private Button userEditBtn;
    @FXML private ImageView userImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
