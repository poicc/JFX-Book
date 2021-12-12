package top.mqxu.book.manage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import top.mqxu.book.manage.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController
 *
 * @author mqxu
 */
public class MainController implements Initializable {
    @FXML
    private StackPane mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane anchorPane = new FXMLLoader(Main.class.getResource("fxml/default.fxml")).load();
            mainContainer.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listDefault() throws Exception {
        switchView("default.fxml");
    }

    public void listType() throws Exception {
        switchView("category.fxml");
    }

    public void showWebView() throws Exception {
        switchView("webview.fxml");
    }

    public void listBook() throws Exception {
        switchView("book.fxml");
    }

    public void viewBook() throws Exception {
        switchView("view_book.fxml");
    }

    public void listBookAnalysis() throws Exception {
        switchView("book_analysis.fxml");
    }

    public void listAdmin() throws Exception {
        switchView("admin.fxml");
    }

    public void listUser() throws Exception {
        switchView("user.fxml");
    }

    public void listUserAnalysis() throws Exception {
        switchView("user_analysis.fxml");
    }

    private void switchView(String fileName) throws Exception {
        //清空原有内容
        mainContainer.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(Main.class.getResource("fxml/" + fileName)).load();
        mainContainer.getChildren().add(anchorPane);
    }

    public void exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainContainer.getScene().getWindow();
        stage.close();
    }
}
