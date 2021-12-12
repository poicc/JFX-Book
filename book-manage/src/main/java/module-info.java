module top.mqxu.book.manage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires hutool.all;
    requires java.sql;
    requires lombok;

    opens top.mqxu.book.manage to javafx.fxml;
    exports top.mqxu.book.manage;

    opens top.mqxu.book.manage.controller to javafx.fxml;
    exports top.mqxu.book.manage.controller;

    opens top.mqxu.book.manage.entity to javafx.fxml;
    exports top.mqxu.book.manage.entity;

    opens top.mqxu.book.manage.dao to javafx.fxml;
    exports top.mqxu.book.manage.dao;

    opens top.mqxu.book.manage.util to javafx.fxml;
    exports top.mqxu.book.manage.util;
}