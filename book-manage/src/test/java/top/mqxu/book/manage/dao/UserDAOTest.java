package top.mqxu.book.manage.dao;

import cn.hutool.db.Entity;
import org.junit.jupiter.api.Test;
import top.mqxu.book.manage.entity.User;
import top.mqxu.book.manage.util.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: mqxu
 * @date: 2021-12-10
 **/
class UserDAOTest {

    @Test
    void findUser() throws SQLException {
        Entity entity = DaoFactory.getUserDAOInstance().findUser("13951905171", "123456");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(entity.getStr("create_date"), fmt);
        User user = User.builder()
                .id(entity.getLong("id"))
                .mobile(entity.getStr("mobile"))
                .password(entity.getStr("password"))
                .username(entity.getStr("username"))
                .email(entity.getStr("email"))
                .avatar(entity.getStr("avatar"))
                .identity(entity.getStr("identity"))
                .department(entity.getStr("department"))
                .createDate(date)
                .build();
        System.out.println(user);
    }
}