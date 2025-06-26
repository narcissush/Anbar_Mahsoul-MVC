package anbar.controller;

import anbar.controller.validation.UserValidation;
import anbar.model.entity.User;
import anbar.model.entity.enums.Gender;
import anbar.model.service.UserRegisterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserRegisterController implements Initializable {
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
    private ToggleGroup genderGroup;
    @FXML
    private DatePicker userBirthDate;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt,repeatPasswordTxt;
    @FXML
    private Label validationNationalIdLbl,validationNameLbl,validationFamilyLbl,validationUserNameLbl,validationPasswordLbl,validationPasswordReapeatLbl;
    @FXML
    private Button saveBtn, backBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
resetForm();
        saveBtn.setOnAction(event -> {
            if (validate()) {
                try {
                    RadioButton selectedGenderRdo = (RadioButton) genderGroup.getSelectedToggle();

                    User user = User.builder()
                            //.id(Integer.parseInt(userIdTxt.getText()))
                            .nationalId(userNationalIdTxt.getText())
                            .name(userFirstNameTxt.getText())
                            .family(userFamilyTxt.getText())
                            .gender(Gender.valueOf(selectedGenderRdo.getText()))
                            .birthDate(userBirthDate.getValue())
                            .username(usernameTxt.getText())
                            .password(passwordTxt.getText())
                            .build();
                    UserRegisterService.save(user);
                    resetForm();
                    new Alert(Alert.AlertType.INFORMATION, "user is created", ButtonType.OK).show();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.show();
                }
            }
        });
        backBtn.setOnAction(event -> {
            Stage currentStage = (Stage) backBtn.getScene().getWindow();
            currentStage.close();
        });
    }
    private void resetForm(){
        userIdTxt.clear();
        userNationalIdTxt.clear();
        userFirstNameTxt.clear();
        userFamilyTxt.clear();
        genderGroup.selectToggle(userFamaleRdo);
        userBirthDate.setValue(LocalDate.now());
        usernameTxt.clear();
        passwordTxt.clear();
        userIdTxt.setDisable(true);
    }

    private boolean validate() {

        boolean valid = true;
        if (!UserValidation.isValidName(userFirstNameTxt.getText())) {
            validationNameLbl.setText("نام شامل حروف فارسی");
            validationNameLbl.setVisible(true);
            valid = false;
        }else validationNameLbl.setVisible(false);


        if (!UserValidation.isValidFamilye(userFamilyTxt.getText())) {
            validationFamilyLbl.setText("نام خانوادگی شامل حروف فارسی");
            validationFamilyLbl.setVisible(true);
            valid = false;
        }else validationFamilyLbl.setVisible(false);

        if (!UserValidation.isValidNationalId(userNationalIdTxt.getText())) {
            validationNationalIdLbl.setText("کد ملی شامل 10 رقم");
            validationNationalIdLbl.setVisible(true);
            valid = false;
        }else validationNationalIdLbl.setVisible(false);

        if (!UserValidation.isValidUserName(usernameTxt.getText())) {
            validationUserNameLbl.setText("شامل حروف انگیسی-اعداد-خط تیره-نقطه");
            validationUserNameLbl.setVisible(true);
            valid = false;
        }else validationUserNameLbl.setVisible(false);

        if (!UserValidation.isValidPassword(passwordTxt.getText())) {
            validationPasswordLbl.setText("شامل حروف انگیسی-اعداد-@#$%^&+=!");
            validationPasswordLbl.setVisible(true);
            valid = false;
        }else validationPasswordLbl.setVisible(false);

        if (!passwordTxt.getText().equals(repeatPasswordTxt.getText())) {
            validationPasswordReapeatLbl.setText("پسورد یکسان نمی باشد");
            validationPasswordReapeatLbl.setVisible(true);
            valid = false;
        }else validationPasswordReapeatLbl.setVisible(false);

        if (!valid) {
            return false;
        } else return true;

    }
}
