package anbar.controller;


import anbar.FormManager;
import anbar.model.entity.User;
import anbar.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField userLoginTxt;

    @FXML
    private PasswordField passwordLoginTxt;

    @FXML
    private Button loginBtn, registerBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBtn.setOnAction(event -> {
            try {
                User user = UserService.findByUserAndPassword(userLoginTxt.getText(), passwordLoginTxt.getText());
                AppState.user = user;
                FormManager formManager = new FormManager();
                formManager.showMainFormController();

                Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                currentStage.close();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        registerBtn.setOnAction(event -> {
            try {
                FormManager formManager = new FormManager();
                formManager.showUserRegisterController();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }
}
