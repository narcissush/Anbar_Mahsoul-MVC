package anbar;

import anbar.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormManager {
    public static mainFormController mainFormController;
    public static LoginController loginController;
    public static UserController userController;
    public static UserRegisterController userRegisterController;
    public static ProductController productController;
    public static SupplierController supplierController;
    public static TransactionController transactionController;
    public static TransactionReportController transactionReportController;


    private Stage mainFormStage;
    private Stage loginStage;
    private Stage userStage;
    private Stage userRegisterStage;
    private Stage productStage;
    private Stage supplierStage;
    private Stage transactionStage;
    private Stage transactionReportStage;


    public mainFormController showMainFormController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainForm.fxml"));
        Parent root = (Parent)loader.load();
        mainFormController = (mainFormController) loader.getController();
        Scene scene = new Scene(root);
        this.mainFormStage = new Stage();
        this.mainFormStage.setScene(scene);
        this.mainFormStage.show();
        return mainFormController;
    }

    public LoginController showLoginController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/LoginForm.fxml"));
        Parent root = (Parent)loader.load();
        loginController = (LoginController) loader.getController();
        Scene scene = new Scene(root);
        this.loginStage = new Stage();
        this.loginStage.setScene(scene);
        this.loginStage.setTitle("ورود");
        this.loginStage.show();
        return loginController;
    }

    public UserController showUserController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UserTab.fxml"));
        Parent root = (Parent)loader.load();
        userController = (UserController) loader.getController();
        Scene scene = new Scene(root);
        this.userStage = new Stage();
        this.userStage.setScene(scene);
        this.userStage.show();
        return userController;
    }

    public UserRegisterController showUserRegisterController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UserRegisterForm.fxml"));
        Parent root = (Parent)loader.load();
        userRegisterController = (UserRegisterController) loader.getController();
        Scene scene = new Scene(root);
        this.userRegisterStage = new Stage();
        this.userRegisterStage.setScene(scene);
        this.userRegisterStage.show();
        return userRegisterController;
    }

    public ProductController showProductController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/ProductTab.fxml"));
        Parent root = (Parent)loader.load();
        productController = (ProductController) loader.getController();
        Scene scene = new Scene(root);
        this.productStage = new Stage();
        this.productStage.setScene(scene);
        this.productStage.show();
        return productController;
    }

    public SupplierController showSupplierController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/SupplierTab.fxml"));
        Parent root = (Parent)loader.load();
        supplierController = (SupplierController) loader.getController();
        Scene scene = new Scene(root);
        this.supplierStage = new Stage();
        this.supplierStage.setScene(scene);
        this.supplierStage.show();
        return supplierController;
    }

    public TransactionController showTransactionController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/Transaction.fxml"));
        Parent root = (Parent)loader.load();
        transactionController = (TransactionController) loader.getController();
        Scene scene = new Scene(root);
        this.transactionStage = new Stage();
        this.transactionStage.setScene(scene);
        transactionStage.setTitle("ثبت ورود و خروج کالا");
        this.transactionStage.show();
        return transactionController;
    }

    public TransactionReportController showTransactionReportController() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/TransactionReport.fxml"));
        Parent root = (Parent)loader.load();
        transactionReportController = (TransactionReportController) loader.getController();
        Scene scene = new Scene(root);
        this.transactionReportStage = new Stage();
        this.transactionReportStage.setScene(scene);
        this.transactionReportStage.setTitle("گزارشات");
        this.transactionReportStage.show();
        return transactionReportController;
    }




}
