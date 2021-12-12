package top.mqxu.book.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import top.mqxu.book.manage.Main;
import top.mqxu.book.manage.dao.UserDAO;
import top.mqxu.book.manage.entity.User;
import top.mqxu.book.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 读者信息控制器
 *
 * @author mqxu
 */
public class UserController implements Initializable {
    @FXML
    private FlowPane userPane;

    private final UserDAO userDAO = DaoFactory.getUserDAOInstance();
    private List<Entity> userList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userList = userDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showUsers(userList);
    }

    /**
     * 遍历userList集合，创建HBox来显示每个用户信息
     *
     * @param userList 用户列表
     */
    private void showUsers(List<Entity> userList) {
        //移除之前的记录
        userPane.getChildren().clear();
        for (Entity entity : userList) {
            HBox hBox = new HBox();
            hBox.setPrefSize(300, 240);
            hBox.getStyleClass().add("box");
            hBox.setSpacing(30);
            //左边垂直布局放头像和身份
            VBox leftBox = new VBox();
            leftBox.setAlignment(Pos.TOP_CENTER);
            leftBox.setSpacing(30);
            ImageView imageView = new ImageView(new Image(entity.getStr("avatar")));
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            Circle circle = new Circle();
            circle.setCenterX(40.0);
            circle.setCenterY(40.0);
            circle.setRadius(40.0);
            imageView.setClip(circle);
            Label roleLabel = new Label(entity.getStr("role"));
            leftBox.getChildren().addAll(imageView, roleLabel);
            //右边垂直布局放姓名、部门、邮箱、电话
            VBox rightBox = new VBox();
            rightBox.setSpacing(15);
            Label nameLabel = new Label(entity.getStr("username"));
            nameLabel.getStyleClass().add("font-title");
            Label departmentLabel = new Label(entity.getStr("department"));
            Label emailLabel = new Label(entity.getStr("email"));
            Label mobileLabel = new Label(entity.getStr("mobile"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Label dateLabel = new Label(df.format(entity.getDate("create_date")));
            Button delBtn = new Button("删除");
            delBtn.getStyleClass().add("warning-theme");
            rightBox.getChildren().addAll(nameLabel, departmentLabel,
                    emailLabel, mobileLabel, dateLabel, delBtn);
            //左右两个垂直布局加入水平布局
            hBox.getChildren().addAll(leftBox, rightBox);
            //水平布局加入大的内容容器
            userPane.getChildren().add(hBox);
            //删除按钮事件
            delBtn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认对话框");
                alert.setContentText("确定要删除这行记录吗?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        long id = entity.getLong("id");
                        //从底层删除掉这行记录
                        userDAO.deleteById(id);
                        //从流式面板移除当前这个人的布局
                        userPane.getChildren().remove(hBox);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 新增用户方法
     */
    public void addUser() throws SQLException {
        User user = new User();
        //新建一个窗口
        Stage stage = new Stage();
        stage.setTitle("新增用户界面");
        //创建一个垂直布局，用来放新增用户的各个组件
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 10, 10, 10));
        TextField usernameField = new TextField("请输入用户名");
        TextField avatarField = new TextField("请输入头像地址");
        //性别，两个单选按钮为一个组，教师单选按钮默认被选中
        HBox roleBox = new HBox();
        roleBox.setSpacing(20);
        ToggleGroup group = new ToggleGroup();
        RadioButton teacherButton = new RadioButton("教师");
        teacherButton.setToggleGroup(group);
        teacherButton.setSelected(true);
        teacherButton.setUserData("教师");
        RadioButton studentButton = new RadioButton("学生");
        studentButton.setToggleGroup(group);
        studentButton.setUserData("学生");
        roleBox.getChildren().addAll(teacherButton, studentButton);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            //设置身份（单选）
            System.out.println(group.getSelectedToggle().getUserData().toString());
            user.setIdentity(group.getSelectedToggle().getUserData().toString());
        });
        //院系部门数组
        String[] departments = {"机械工程学院", "电气工程学院", "航空工程学院", "交通工程学院",
                "计算机与软件学院", "经济管理学院", "商务贸易学院", "艺术设计学院"};
        //数组转为List
        List<String> list = Arrays.asList(departments);
        //将list中的数据加入observableList
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        //创建院系下拉框
        ComboBox<String> depComboBox = new ComboBox<>();
        depComboBox.setPromptText("请选择院系");
        //给下拉框初始化值
        depComboBox.setItems(observableList);
        //下拉框选项改变事件
        depComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //将选中的值设置给读者的部门属性
            user.setDepartment(newValue);
        });
        //创建一个日期选择器对象，并初始化值为当前日期
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        //手机号输入框
        TextField mobileField = new TextField("请输入手机号");
        //密码输入框
        TextField passwordField = new PasswordField();
        //邮箱输入框
        TextField emailField = new TextField("请输入邮箱");
        //新增按钮
        Button addBtn = new Button("新增");
        addBtn.getStyleClass().add("blue-theme");
        vBox.getChildren().addAll(usernameField, avatarField, roleBox, depComboBox, datePicker,
                mobileField, passwordField, emailField, addBtn);
        Scene scene = new Scene(vBox, 600, 380);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("img/logo.png"))));
        stage.setScene(scene);
        stage.show();
        //点击新增按钮，将界面数据封装成一个Reader对象，写入数据库
        addBtn.setOnAction(event -> {
            String usernameString = usernameField.getText().trim();
            String passwordString = passwordField.getText().trim();
            String avatarString = avatarField.getText().trim();
            LocalDate createDate = datePicker.getValue();
            String emailString = emailField.getText().trim();
            String mobileString = mobileField.getText().trim();
            user.setMobile(mobileString);
            user.setPassword(passwordString);
            user.setEmail(emailString);
            user.setUsername(usernameString);
            user.setAvatar(avatarString);
            user.setCreateDate(createDate);

            System.out.println(user);
            try {
                userDAO.insert(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("新增用户成功!");
            alert.showAndWait();
            stage.close();
            //重新读取数据显示
            try {
                userList = userDAO.selectAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            showUsers(userList);
        });
    }
}
