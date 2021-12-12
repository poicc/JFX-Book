package top.mqxu.book.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import top.mqxu.book.manage.dao.BookDAO;
import top.mqxu.book.manage.dao.CategoryDAO;
import top.mqxu.book.manage.entity.Book;
import top.mqxu.book.manage.entity.Category;
import top.mqxu.book.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AddBookController
 *
 * @author mqxu
 */
public class AddBookController implements Initializable {

    @FXML
    private ComboBox<Category> bookCategory;

    @FXML
    private TextField bookName, bookAuthor, bookPrice, bookCover, bookStock;

    @FXML
    private TextArea bookSummary;

    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    public ObservableList<Book> getBookData() {
        return bookData;
    }

    public void setBookData(ObservableList<Book> bookData) {
        this.bookData = bookData;
    }

    private final ObservableList<Category> categoryData = FXCollections.observableArrayList();

    private final BookDAO bookDAO = DaoFactory.getBookDAOInstance();

    private final CategoryDAO categoryDAO = DaoFactory.getCategoryDAOInstance();

    private List<Entity> entityList = null;

    private Long categoryId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            entityList = categoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        }
        for (Entity entity : entityList) {
            Category category = new Category();
            category.setId(entity.getLong("id"));
            category.setName(entity.getStr("name"));
            categoryData.add(category);
        }
        bookCategory.setItems(categoryData);
        bookCategory.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    categoryId = newValue.getId();
                }
        );
    }

    public void addBook() throws Exception {
        String name = bookName.getText();
        String author = bookAuthor.getText();
        String price = bookPrice.getText();
        String stock = bookStock.getText();
        String cover = bookCover.getText();
        String summary = bookSummary.getText();
        System.out.println(stock);
        Book book = new Book();
        book.setCategoryId(categoryId);
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(Double.parseDouble(price));
        book.setStock(Integer.parseInt(stock));
        book.setCover(cover);
        book.setSummary(summary);
        long id = bookDAO.insert(book);
        book.setId(id);
        this.getBookData().add(book);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增图书成功!");
        alert.showAndWait();
        Stage stage = (Stage) bookName.getScene().getWindow();
        stage.close();
    }
}
