package top.mqxu.book.manage.dao;

import cn.hutool.db.Entity;
import top.mqxu.book.manage.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * UserDAO
 *
 * @author mqxu
 */
public interface UserDAO {
    /**
     * 根据手机号和密码查找用户
     *
     * @param mobile   手机号
     * @param password 密码
     * @return 用户
     * @throws SQLException
     */
    Entity findUser(String mobile, String password) throws SQLException;

    /**
     * 新增一个用户，返回自增主键
     *
     * @param user 用户对象
     * @return long
     * @throws SQLException
     */
    Long insert(User user) throws SQLException;

    /**
     * 查询所有读者信息
     *
     * @return List<Entity>
     * @throws SQLException
     */
    List<Entity> selectAll() throws SQLException;

    /**
     * 根据id删除用户
     *
     * @param id id
     * @return int
     * @throws SQLException
     */
    int deleteById(long id) throws SQLException;

}
