package top.mqxu.book.manage.util;


import top.mqxu.book.manage.dao.AdminDAO;
import top.mqxu.book.manage.dao.BookDAO;
import top.mqxu.book.manage.dao.UserDAO;
import top.mqxu.book.manage.dao.CategoryDAO;
import top.mqxu.book.manage.dao.impl.AdminDAOImpl;
import top.mqxu.book.manage.dao.impl.BookDAOImpl;
import top.mqxu.book.manage.dao.impl.UserDAOImpl;
import top.mqxu.book.manage.dao.impl.CategoryDAOImpl;

/**
 * 工厂类，用静态方法来生成各个DAO实例
 *
 * @author mqxu
 */
public class DaoFactory {
    /**
     * 静态方法，返回CategoryDAO实现类的对象
     *
     * @return CategoryDAO
     */
    public static CategoryDAO getCategoryDAOInstance() {
        return new CategoryDAOImpl();
    }

    /**
     * 静态方法，返回TypeDAO实现类的对象
     *
     * @return BookDAO
     */
    public static BookDAO getBookDAOInstance() {
        return new BookDAOImpl();
    }

    /**
     * 静态方法，返回UserDAO实现类的对象
     *
     * @return UserDAO
     */
    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }

    /**
     * 静态方法，返回AdminDAO实现类的对象
     *
     * @return AdminDAO
     */
    public static AdminDAO getAdminDAOInstance() {
        return new AdminDAOImpl();
    }
}
