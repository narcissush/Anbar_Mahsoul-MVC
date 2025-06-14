package anbar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/appForm.fxml")));
            primaryStage.setTitle("ورود");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
