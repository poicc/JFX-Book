package top.mqxu.book.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import top.mqxu.book.manage.dao.BookDAO;
import top.mqxu.book.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * ViewBookController
 *
 * @author mqxu
 */
public class ViewBookController implements Initializable {
    @FXML
    private FlowPane bookPane;

    private List<Entity> list = new ArrayList<>();

    private final BookDAO bookDAO = DaoFactory.getBookDAOInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            list = bookDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showBooks(list);
    }

    private void showBooks(List<Entity> list) {
        ObservableList<Node> observableList = bookPane.getChildren();
        bookPane.getChildren().removeAll(observableList);
        for (Entity entity : list) {
            VBox vBox = new VBox();
            vBox.setPrefSize(240, 300);
            vBox.getStyleClass().add("box");
            vBox.setSpacing(10);
            vBox.setAlignment(Pos.CENTER);
            ImageView imageView = new ImageView(new Image(entity.getStr("cover")));
            imageView.setFitWidth(100);
            imageView.setFitHeight(120);
            Label nameLabel = new Label(entity.getStr("name"));
            nameLabel.getStyleClass().add("font-title");
            Label authorLabel = new Label("作者：" + entity.getStr("author"));
            Label priceLabel = new Label("价格：" + entity.getDouble("price"));
            Label stockLabel = new Label("库存：" + entity.getDouble("stock"));
            Button delBtn = new Button("删除");
            delBtn.getStyleClass().add("warning-theme");
            vBox.getChildren().addAll(imageView, nameLabel, authorLabel, priceLabel, stockLabel, delBtn);
            bookPane.getChildren().add(vBox);
            delBtn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认对话框");
                alert.setContentText("确定要删除这行记录吗?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        long id = entity.getLong("id");
                        bookDAO.deleteById(id);
                        bookPane.getChildren().remove(vBox);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
