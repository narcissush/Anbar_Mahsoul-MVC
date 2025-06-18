package anbar.controller;

import anbar.model.entity.User;
import anbar.model.entity.enums.Gender;
import anbar.model.service.UserRegisterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
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
    private PasswordField passwordTxt;
    @FXML
    private Button saveBtn, backBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
resetForm();
        saveBtn.setOnAction(event -> {
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
                System.out.println(e.getMessage());
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
        userBirthDate.setValue(null);
        usernameTxt.clear();
        passwordTxt.clear();
        userIdTxt.setDisable(true);

    }
}
