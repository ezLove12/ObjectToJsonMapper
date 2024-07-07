package org.viethm.json;

import java.util.List;

@JSONRootKeyMapper(key = "bookStore")
public class BookStore {
    @JSONKeyMapper(key = "name")
    private String name;

    @JSONKeyMapper(key = "location")
    private String location;

    @JSONKeyWrapperMapper(key = "bookList")
    private List<Book> bookList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
