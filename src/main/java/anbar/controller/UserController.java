package anbar.controller;


import anbar.controller.exceptions.UserException;
import anbar.model.entity.User;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Person;
import anbar.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
            loginUser = UserService.getLoginUser();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        fillUserForm();

        userEditBtn.setOnAction(event -> {
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
        });
        userQuitBtn.setOnAction(event -> {
            try {
                Stage secondStage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
                secondStage.setScene(scene);
                secondStage.setTitle("ورود");
                secondStage.show();

                Stage currentStage = (Stage) userQuitBtn.getScene().getWindow();
                UserService.loginUser = null;
                currentStage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }

    private void fillUserForm() {
        try {
            User user = UserService.getLoginUser();
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
}
