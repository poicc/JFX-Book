package top.mqxu.book.manage.dao.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import top.mqxu.book.manage.dao.UserDAO;
import top.mqxu.book.manage.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 读者DAO的实现类
 *
 * @author mqxu
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_user ");
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_user").set("id", id)
        );
    }

    @Override
    public Entity findUser(String mobile, String password) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_user WHERE mobile = ? AND password = ? ", mobile, SecureUtil.md5(password));
    }

    @Override
    public Long insert(User user) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_user")
                        .set("mobile", user.getMobile())
                        .set("password", SecureUtil.md5(user.getPassword()))
                        .set("email", user.getEmail())
                        .set("username", user.getUsername())
                        .set("avatar", user.getAvatar())
                        .set("identity", user.getIdentity())
                        .set("department", user.getDepartment())
                        .set("create_date", user.getCreateDate())
        );
    }
}
