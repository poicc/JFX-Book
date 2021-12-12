package top.mqxu.book.manage.dao.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import top.mqxu.book.manage.dao.AdminDAO;

import java.sql.SQLException;

/**
 * @description:
 * @author: mqxu
 * @date: 2021-12-10
 **/
public class AdminDAOImpl implements AdminDAO {
    @Override
    public Entity findAdmin(String account, String password) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_admin WHERE account = ? AND password = ? ", account, SecureUtil.md5(password));
    }
}
