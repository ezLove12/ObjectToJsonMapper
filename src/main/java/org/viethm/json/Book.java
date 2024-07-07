package org.viethm.json;

@JSONRootKeyMapper(key = "book")
public class Book {
    @JSONKeyMapper(key = "name")
    private String name;

    @JSONKeyMapper(key = "author")
    private String author;

    @JSONKeyMapper(key = "publisher")
    private String publisher;

    @JSONKeyMapper(key = "isbn")
    private String isbn;

    @JSONKeyMapper(key = "quantity")
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
