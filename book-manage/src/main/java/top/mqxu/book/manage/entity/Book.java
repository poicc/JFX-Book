package top.mqxu.book.manage.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 图书实体类
 *
 * @author mqxu
 */
public class Book {
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleLongProperty categoryId = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty author = new SimpleStringProperty("");
    private final SimpleDoubleProperty price = new SimpleDoubleProperty();
    private final SimpleStringProperty cover = new SimpleStringProperty("");
    private final SimpleStringProperty summary = new SimpleStringProperty("");
    private final SimpleIntegerProperty stock = new SimpleIntegerProperty();

    public Book() {
    }

    public Book(Long id, Long categoryId, String name, String author, Double price, String cover, String summary, Integer stock) {
        setId(id);
        setCategoryId(categoryId);
        setName(name);
        setAuthor(author);
        setPrice(price);
        setCover(cover);
        setSummary(summary);
        setStock(stock);
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

    public long getCategoryId() {
        return categoryId.get();
    }

    public SimpleLongProperty categoryIdProperty() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId.set(categoryId);
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

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getCover() {
        return cover.get();
    }

    public SimpleStringProperty coverProperty() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover.set(cover);
    }

    public String getSummary() {
        return summary.get();
    }

    public SimpleStringProperty summaryProperty() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary.set(summary);
    }

    public int getStock() {
        return stock.get();
    }

    public SimpleIntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
}
