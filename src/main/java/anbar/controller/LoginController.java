package anbar.controller;


import anbar.model.entity.User;
import anbar.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField userLoginTxt,passwordLoginTxt;
    @FXML private Button loginBtn,registerBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBtn.setOnAction(event -> {
            try {
                User user = new User();
                user = UserService.findByUserAndPassword(userLoginTxt.getText(), passwordLoginTxt.getText());
                if (user != null) {
                    Stage secondStage = new Stage();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/appForm.fxml")));
                    secondStage.setScene(scene);
                    secondStage.setTitle("نرم افزار انبارداری");
                    secondStage.show();

                    Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                    currentStage.close();

                } else {
                    new Alert(Alert.AlertType.INFORMATION, "User Not Found", ButtonType.OK).show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        registerBtn.setOnAction(event -> {
            try {
                Stage secondStage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/UserRegisterForm.fxml")));
                secondStage.setScene(scene);
                secondStage.setTitle("ثبت نام کاربر");
                secondStage.show();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });


}
}
