package top.mqxu.book.manage.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 图书类别实体类
 *
 * @author mqxu
 */
public class Category {
    /**
     * 以JavaFX属性绑定的形式，定义和数据表字段id和type_name对应的属性，注意命名规范
     */
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty("");

    public Category() {
    }

    public Category(long id, String name) {
        setId(id);
        setName(name);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
