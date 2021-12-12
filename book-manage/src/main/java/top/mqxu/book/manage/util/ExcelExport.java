package top.mqxu.book.manage.util;

import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import top.mqxu.book.manage.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mqxu
 */
public class ExcelExport {
    public static void export(List<Entity> bookList) {
        List<Book> books = new ArrayList<>();
        for (Entity entity : bookList) {
            Book book = new Book();
            book.setId(entity.getLong("id"));
            book.setCategoryId(entity.getLong("category_id"));
            book.setName(entity.getStr("name"));
            book.setAuthor(entity.getStr("author"));
            book.setPrice(entity.getDouble("price"));
            book.setCover(entity.getStr("cover"));
            book.setSummary(entity.getStr("summary"));
            books.add(book);
        }
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("/Users/mqxu/Desktop/books.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "图书信息表");
        // 一次性写出内容，使用默认样式
        writer.write(books);
        // 关闭writer，释放内存
        writer.close();
    }
}
