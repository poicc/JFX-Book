package top.mqxu.book.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import top.mqxu.book.manage.dao.BookDAO;
import top.mqxu.book.manage.dao.CategoryDAO;
import top.mqxu.book.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * BookAnalysisController
 *
 * @author mqxu
 */
public class BookAnalysisController implements Initializable {
    @FXML
    private StackPane pieChartPane, barChartPane;

    private final CategoryDAO categoryDAO = DaoFactory.getCategoryDAOInstance();
    private final BookDAO bookDAO = DaoFactory.getBookDAOInstance();

    private final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieChart();
        initBarChart();
    }

    private void initPieChart() {
        List<Entity> categoryList = null;
        try {
            categoryList = categoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert categoryList != null;
        for (Entity entity : categoryList) {
            try {
                int count = bookDAO.countByCategory(entity.getLong("id"));
                pieChartData.add(new PieChart.Data(entity.getStr("name"), count));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("按图书类别统计饼图");
        pieChartPane.getChildren().add(chart);
    }

    private void initBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("根据类别统计柱形图");
        xAxis.setLabel("图书类别");
        yAxis.setLabel("图书数量");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2021年统计数据");
        List<Entity> categoryList = null;
        try {
            categoryList = categoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert categoryList != null;
        for (Entity entity : categoryList) {
            try {
                int count = bookDAO.countByCategory(entity.getLong("id"));
                series1.getData().add(new XYChart.Data(entity.getStr("name"), count));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        bc.getData().addAll(series1);
        barChartPane.getChildren().add(bc);
    }
}
