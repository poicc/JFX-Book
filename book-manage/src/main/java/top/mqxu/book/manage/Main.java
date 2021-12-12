package top.mqxu.book.manage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author mqxu
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 700);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("登录");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("img/logo.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}