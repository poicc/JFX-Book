package top.mqxu.book.manage.controller;

import cn.hutool.db.Entity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import top.mqxu.book.manage.Main;
import top.mqxu.book.manage.dao.AdminDAO;
import top.mqxu.book.manage.dao.UserDAO;
import top.mqxu.book.manage.util.DaoFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author mqxu
 */
public class AdminController implements Initializable {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;

    private final AdminDAO adminDAO = DaoFactory.getAdminDAOInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login() throws Exception {
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        Entity admin = adminDAO.findAdmin(account, password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        if (admin != null) {
            alert.setContentText("登录成功!");
            alert.showAndWait();
            Stage mainStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
            mainStage.setTitle("book manage system");
            mainStage.setMaximized(true);
            mainStage.setScene(scene);
            mainStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("img/logo.png"))));
            mainStage.show();
            Stage loginStage = (Stage) accountField.getScene().getWindow();
            loginStage.close();
        } else {
            alert.setContentText("账号或密码错误，登录失败!");
            alert.showAndWait();
        }
    }
}
