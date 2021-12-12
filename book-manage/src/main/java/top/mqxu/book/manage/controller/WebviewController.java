package top.mqxu.book.manage.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @description:
 * @author: mqxu
 * @date: 2021-12-09
 **/
public class WebviewController implements Initializable {
    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webView.getEngine();
        // 禁用右键
        webView.setContextMenuEnabled(false);
        webEngine.load("http://www.nlc.cn/");
    }
}
