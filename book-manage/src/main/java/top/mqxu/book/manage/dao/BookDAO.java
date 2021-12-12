package top.mqxu.book.manage.dao;

import cn.hutool.db.Entity;
import top.mqxu.book.manage.entity.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * BookDAO
 *
 * @author mqxu
 */
public interface BookDAO {

    /**
     * 新增图书，返回自增主键
     *
     * @param book book
     * @return Long
     */
    Long insert(Book book) throws SQLException;

    /**
     * 根据id删除图书
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;

    /**
     * 更新图书信息
     *
     * @param book book
     * @return int
     */
    int update(Book book) throws SQLException;


    /**
     * 查询所有图书
     *
     * @return 图书集合
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询图书信息
     *
     * @param id id
     * @return Entity
     */
    Entity getById(long id) throws SQLException;

    /**
     * 根据书名关键词模糊查询图书
     *
     * @param keywords 关键字
     * @return 查询结果
     */
    List<Entity> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据图书类别查询图书
     *
     * @param categoryId 类别id
     * @return 查询结果
     */
    List<Entity> selectByCategoryId(long categoryId) throws SQLException;

    /**
     * 根据图书类别统计图书数量
     *
     * @param categoryId 类别id
     * @return int
     */
    int countByCategory(long categoryId) throws SQLException;
}
