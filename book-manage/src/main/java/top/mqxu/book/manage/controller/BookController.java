package top.mqxu.book.manage.controller;

import cn.hutool.db.Entity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import top.mqxu.book.manage.Main;
import top.mqxu.book.manage.dao.BookDAO;
import top.mqxu.book.manage.dao.CategoryDAO;
import top.mqxu.book.manage.entity.Book;
import top.mqxu.book.manage.entity.Category;
import top.mqxu.book.manage.util.ComponentUtil;
import top.mqxu.book.manage.util.DaoFactory;
import top.mqxu.book.manage.util.ExcelExport;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * BookController
 *
 * @author mqxu
 */
public class BookController implements Initializable {
    /**
     * 布局文件中的表格视图对象，用来显示数据库中读取的所有图书信息
     */
    @FXML
    private TableView<Book> bookTable;

    /**
     * 布局文件中的下拉框组件对象，用来显示数据库中读取的所有图书类别
     */
    @FXML
    private ComboBox<Category> categoryComboBox;

    /**
     * 布局文件中的输入文本框对象，用来输入搜索关键词
     */
    @FXML
    private TextField keywordsField;

    /**
     * 图书模型数据集合，可以实时相应数据变化，无需刷新
     */
    private final ObservableList<Book> bookData = FXCollections.observableArrayList();

    /**
     * 图书类型模型数据集合
     */
    private final ObservableList<Category> categoryData = FXCollections.observableArrayList();

    /**
     * 图书DAO对象，从DAO工厂通过静态方法获得
     */
    private final BookDAO bookDAO = DaoFactory.getBookDAOInstance();

    /**
     * 图书类别DAO对象
     */
    private final CategoryDAO categoryDAO = DaoFactory.getCategoryDAOInstance();

    /**
     * 图书实体集合，存放数据库图书表各种查询的结果
     */
    private List<Entity> bookList = null;

    /**
     * 类别实体集合，存放数据库类别表查询结果
     */
    private List<Entity> categoryList = null;

    /**
     * 表格中的编辑列
     */
    private final TableColumn<Book, Book> editCol = new TableColumn<>("操作");

    /**
     * 表格中的删除列
     */
    private final TableColumn<Book, Book> delCol = new TableColumn<>("操作");

    /**
     * 初始化方法，通过调用对图书表格和列表下拉框的两个封装方法，实现数据初始化
     *
     * @param location  location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initComBox();
    }

    /**
     * 表格初始化方法
     */
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //1.调用底层查询所有图书的方法，
        try {
            bookList = bookDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示图书模型集合的值
        showBookData(bookList);

        //2.编辑列的相关设置
        editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editCol.setCellFactory(param -> new TableCell<>() {
            //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
            private final Button editButton = ComponentUtil.getButton("编辑", "blue-theme");

            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                //点击编辑按钮，弹出窗口，输入需要修改的图书价格
                editButton.setOnAction(event -> {
                    TextInputDialog dialog = new TextInputDialog("请输入价格");
                    dialog.setTitle("图书修改界面");
                    dialog.setHeaderText("书名：" + book.getName());
                    dialog.setContentText("请输入新的价格:");
                    Optional<String> result = dialog.showAndWait();
                    //确认输入了内容，避免NPE
                    if (result.isPresent()) {
                        //获取输入的新价格并转化成Double数据
                        String priceString = result.get();
                        book.setPrice(Double.parseDouble(priceString));
                        try {
                            //更新图书信息
                            bookDAO.update(book);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        //将编辑列加入图书表格
        bookTable.getColumns().add(editCol);

        //3.删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");

            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("书名：" + book.getName());
                    alert.setContentText("确定要删除这行记录吗?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        bookData.remove(book);
                        try {
                            bookDAO.deleteById(book.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        //将除列加入图书表格
        bookTable.getColumns().add(delCol);

        //4.图书表格双击事件,双击弹出显示图书详情的界面
        bookTable.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                //判断鼠标双击了一行
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //获得该行的图书ID属性
                    long id = row.getItem().getId();
                    Book book = new Book();
                    try {
                        //通过ID到数据库查询到该图书对象的完整信息，因为表格中没有显示全，如
                        //图书封面、摘要等，但是在详情界面需要这些数据，如果不查出完整信息，会有空值异常
                        Entity entity = bookDAO.getById(id);
                        book.setId(entity.getLong("id"));
                        book.setCategoryId(entity.getLong("category_id"));
                        book.setName(entity.getStr("name"));
                        book.setAuthor(entity.getStr("author"));
                        book.setPrice(entity.getDouble("price"));
                        book.setCover(entity.getStr("cover"));
                        book.setSummary(entity.getStr("summary"));
                        book.setStock(entity.getInt("stock"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //  System.out.println(book.getName() + book.getPrice() + book.getCover() + book.getSummary());
                    //创建一个新的图书详情界面窗口
                    Stage bookInfoStage = new Stage();
                    bookInfoStage.setTitle("图书详情界面");
                    //用VBox显示具体图书信息
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPrefSize(600, 400);
                    vBox.setPadding(new Insets(10, 10, 10, 10));
                    Label nameLabel = new Label("书名：" + book.getName());
                    nameLabel.getStyleClass().add("font-title");
                    Label authorLabel = new Label("作者：" + book.getAuthor());
                    Label priceLabel = new Label("价格:" + book.getPrice());
                    Label stockLabel = new Label("库存：" + book.getStock());
                    ImageView bookImgView = new ImageView(new Image(book.getCover()));
                    bookImgView.setFitHeight(150);
                    bookImgView.setFitWidth(120);
                    Label summaryLabel = new Label(book.getSummary());
                    summaryLabel.setPrefWidth(400);
                    summaryLabel.setWrapText(true);
                    summaryLabel.getStyleClass().add("box");
                    vBox.getChildren().addAll(nameLabel, authorLabel, priceLabel, stockLabel, bookImgView, summaryLabel);
                    Scene scene = new Scene(vBox, 640, 480);
                    //因为是一个新的窗口，需要重新读入一下样式表，这个界面就可以使用style.css样式表中的样式了
                    scene.getStylesheets().add("/css/style.css");
                    bookInfoStage.setScene(scene);
                    bookInfoStage.show();
                }
            });
            return row;
        });
    }

    /**
     * 下拉框初始化方法
     */
    private void initComBox() {
        //1.到数据库查询所有的类别
        try {
            categoryList = categoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.遍历categoryList集合，将其加入categoryData模型数据集合
        for (Entity entity : categoryList) {
            Category category = new Category();
            category.setId(entity.getLong("id"));
            category.setName(entity.getStr("name"));
            categoryData.add(category);
        }
        categoryComboBox.setItems(categoryData);

        //4.下拉框选择事件监听，根据选择不同的类别，图书表格中会过滤出该类别的图书
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue.getId() + "," + newValue.getName());
                    //移除掉之前的数据
                    bookTable.getItems().removeAll(bookData);
                    try {
                        //根据选中的类别查询该类别所有图书
                        bookList = bookDAO.selectByCategoryId(newValue.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //重新显示数据
                    showBookData(bookList);
                }
        );
    }

    /**
     * 显示图书表格数据的方法
     *
     * @param bookList 图书列表
     */
    private void showBookData(List<Entity> bookList) {
        for (Entity entity : bookList) {
            Book book = new Book();
            book.setId(entity.getInt("id"));
            book.setName(entity.getStr("name"));
            book.setAuthor(entity.getStr("author"));
            book.setPrice(entity.getDouble("price"));
            book.setStock(entity.getInt("stock"));
            bookData.add(book);
        }
        bookTable.setItems(bookData);
    }

    /**
     * 弹出新增图书界面方法
     */
    public void newBookStage() throws Exception {
        Stage addBookStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/add_book.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
        AddBookController addBookController = fxmlLoader.getController();
        addBookController.setBookData(bookData);
        addBookStage.setTitle("新增图书界面");
        addBookStage.setResizable(false);
        addBookStage.setScene(scene);
        addBookStage.show();
    }

    /**
     * 根据关键词搜索方法
     */
    public void search() {
        bookTable.getItems().removeAll(bookData);
        //获得输入的查询关键字
        String keywords = keywordsField.getText().trim();
        try {
            bookList = bookDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showBookData(bookList);
    }

    /**
     * 数据导出方法，采用hutool提供的工具类
     */
    public void export() {
        ExcelExport.export(bookList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("图书数据已导出!");
        alert.showAndWait();
    }
}
