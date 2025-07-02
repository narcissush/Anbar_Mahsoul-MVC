package anbar.controller;


import anbar.FormManager;
import anbar.controller.validation.UserValidation;
import anbar.model.entity.User;
import anbar.model.entity.enums.Gender;
import anbar.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class UserController implements Initializable {


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
    private Button userEditBtn, userQuitBtn;
    User loginUser = new User();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loginUser = AppState.user;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        fillUserForm();

        userEditBtn.setOnAction(event -> {
            if (validate()) {
                try {
                    RadioButton selectedGenderRdo = (RadioButton) genderToggle.getSelectedToggle();
                    User user = User.builder()
                            .id(Integer.parseInt(userIdTxt.getText()))
                            .nationalId(userNationalIdTxt.getText())
                            .name(userFirstNameTxt.getText())
                            .family(userFamilyTxt.getText())
                            .gender(Gender.valueOf(selectedGenderRdo.getText()))
                            .birthDate(userBirthDate.getValue())
                            .username(usernameTxt.getText())
                            .password(passwordTxt.getText())
                            .build();
                    UserService.edit(user);
                    new Alert(Alert.AlertType.INFORMATION, "user Edited", ButtonType.OK).show();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.show();
                }
            }
        });
        userQuitBtn.setOnAction(event -> {
            try {
                FormManager formManager = new FormManager();
                formManager.showLoginController();

                Stage currentStage = (Stage) userQuitBtn.getScene().getWindow();
                AppState.user = null;
                currentStage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }

    private void fillUserForm() {
        try {
            User user = AppState.user;
            userIdTxt.setText(String.valueOf(user.getId()));
            userNationalIdTxt.setText(String.valueOf(user.getNationalId()));
            userFirstNameTxt.setText(String.valueOf(user.getName()));
            userFamilyTxt.setText(String.valueOf(user.getFamily()));
            if (user.getGender() == Gender.مرد) userMaleRdo.setSelected(true);
            else userFamaleRdo.setSelected(true);
            userBirthDate.setValue(user.getBirthDate());
            usernameTxt.setText(String.valueOf(user.getUsername()));
            passwordTxt.setText(String.valueOf(user.getPassword()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    private boolean validate() {

        StringBuilder errorMessage = new StringBuilder();
        boolean valid = true;
        if (!UserValidation.isValidName(userFirstNameTxt.getText())) {
            errorMessage.append("نام معتبر نمی باشد. نام شامل حروف فارسی" + "/n");
            valid = false;
        }

        if (!UserValidation.isValidFamilye(userFamilyTxt.getText())) {
            errorMessage.append("نام خانوادگی معتبر نمی باشد. نام خانوادگی شامل حروف فارسی" + "/n");
            valid = false;
        }

        if (!UserValidation.isValidNationalId(userNationalIdTxt.getText())) {
            errorMessage.append("کد ملی معتبر نمی باشد. کد ملی شامل 10 رقم " + "/n");
            valid = false;
        }

        if (!UserValidation.isValidUserName(usernameTxt.getText())) {
            errorMessage.append("نام کاربری نامعتبر می باشد.نام کاربری شامل حرف -اعداد-نقطه یا خط تیره" + "/n");
            valid = false;
        }

        if (!UserValidation.isValidPassword(passwordTxt.getText())) {
            errorMessage.append("رمز عبور معتبر نمی باشد. رمز عبور شامل حرف بزرگ و کوچک-اعداد-@#$%^" + "/n");
            valid = false;
        }
        if (!valid) {
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
            alert.show();
            return false;
        } else return true;

    }
}


