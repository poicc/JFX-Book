package top.mqxu.book.manage.dao;

import cn.hutool.db.Entity;

import java.sql.SQLException;

/**
 * @description: AdminDAO
 * @author: mqxu
 * @date: 2021-12-10
 **/
public interface AdminDAO {
    /**
     * 查找管理员
     *
     * @param account  账号
     * @param password 密码
     * @return 管理员
     * @throws SQLException
     */
    Entity findAdmin(String account, String password) throws SQLException;
}
