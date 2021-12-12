package top.mqxu.book.manage.dao;

import cn.hutool.db.Entity;
import top.mqxu.book.manage.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * 图书类别DAO接口
 *
 * @author mqxu
 */
public interface CategoryDAO {

    /**
     * 新增图书类别, 返回自增主键(Long)
     *
     * @param category 图书类别对象
     * @return 自增主键
     */
    Long insert(Category category) throws SQLException;

    /**
     * 根据id删除类别
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;

    /**
     * 查询所有类别
     *
     * @return 查询结果
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询类别信息
     *
     * @param id id
     * @return 查询结果
     */
    Entity getById(int id) throws SQLException;
}
