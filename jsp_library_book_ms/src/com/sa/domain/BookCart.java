// 
// 
// 

package com.sa.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookCart
{
    private Map<String, Book> books;
    
    public BookCart() {
        this.books = new HashMap<String, Book>();
    }
    
    public Map<String, Book> getBooks() {
        return this.books;
    }
    
    public void removeBook(final String bookId) {
        this.books.remove(bookId);
    }
    
    public void clear() {
        this.books.clear();
    }
    
    public boolean isEmpty() {
        return this.books.isEmpty();
    }
    
    public Collection<Book> getItems() {
        return this.books.values();
    }
    
    public Integer getBookNumber() {
        return this.books.size();
    }
    
    public void addBook(final Book book) {
        final Book bookTemp = this.books.get(book.getBookId());
        if (bookTemp == null) {
            this.books.put(book.getBookId(), book);
        }
    }
    
    public boolean hasBook(final Integer id) {
        return this.books.containsKey(id);
    }
}
