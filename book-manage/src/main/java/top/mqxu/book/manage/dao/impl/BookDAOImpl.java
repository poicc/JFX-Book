package top.mqxu.book.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.mqxu.book.manage.dao.BookDAO;
import top.mqxu.book.manage.entity.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * BookDAOImpl
 *
 * @author mqxu
 */
public class BookDAOImpl implements BookDAO {
    @Override
    public Long insert(Book book) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_book")
                        .set("category_id", book.getCategoryId())
                        .set("name", book.getName())
                        .set("author", book.getAuthor())
                        .set("price", book.getPrice())
                        .set("cover", book.getCover())
                        .set("summary", book.getSummary())
                        .set("stock", book.getStock())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_book").set("id", id)
        );
    }

    @Override
    public int update(Book book) throws SQLException {
        //只修改了图书的价格和库存
        return Db.use().update(
                Entity.create().set("price", book.getPrice())
                        .set("stock", book.getStock()),
                Entity.create("t_book").set("id", book.getId())
        );
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_book ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_book WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_book", "name", keywords, Condition.LikeType.Contains);
    }

    @Override
    public List<Entity> selectByCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_book WHERE category_id = ? ", categoryId);
    }

    @Override
    public int countByCategory(long categoryId) throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_book WHERE category_id = ? ", categoryId).intValue();
    }
}
