package top.mqxu.book.manage.dao;

import cn.hutool.db.Entity;
import org.junit.jupiter.api.Test;
import top.mqxu.book.manage.entity.Admin;
import top.mqxu.book.manage.entity.User;
import top.mqxu.book.manage.util.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: mqxu
 * @date: 2021-12-10
 **/
class AdminDAOTest {

    @Test
    void findAdmin() throws SQLException {
        Entity entity = DaoFactory.getAdminDAOInstance().findAdmin("admin", "123456");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime createTime = LocalDateTime.parse(entity.getStr("create_time"), fmt);
        Admin admin = Admin.builder()
                .id(entity.getLong("id"))
                .account(entity.getStr("account"))
                .password(entity.getStr("password"))
                .createTime(createTime)
                .build();
        System.out.println(admin);
    }
}