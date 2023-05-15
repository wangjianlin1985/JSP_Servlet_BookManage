// 
// 
// 

package com.sa.service;

import com.sa.domain.BookCart;
import com.sa.domain.BookBorrow;
import java.util.List;
import com.sa.domain.Book;
import com.sa.web.Page;
import com.sa.web.CriteriaBook;
import com.sa.dao.impl.BookBorrowDaoImpl;
import com.sa.dao.impl.BookDaoImpl;
import com.sa.dao.BookBorrowDao;
import com.sa.dao.BookDao;

public class BookService
{
    private BookDao bookDao;
    private BookBorrowDao bookBorrowDao;
    
    public BookService() {
        this.bookDao = new BookDaoImpl();
        this.bookBorrowDao = new BookBorrowDaoImpl();
    }
    
    public Page<Book> getBookPage(final CriteriaBook cb) {
        return this.bookDao.getBookPage(cb);
    }
    
    public Book getBook(final String id) {
        return this.bookDao.getBook(id);
    }
    
    public List<BookBorrow> getBookBorrow(final String bookId) {
        return this.bookBorrowDao.getBookBorrow(bookId);
    }
    
    public void update(final Book book) {
        this.bookDao.update(book);
    }
    
    public boolean addToCart(final String id, final BookCart bc) {
        final Book book = this.bookDao.getBook(id);
        if (book != null) {
            bc.addBook(book);
            return true;
        }
        return false;
    }
    
    public void removeBookFromCart(final String bookId, final BookCart bc) {
        bc.removeBook(bookId);
    }
    
    public void clear(final BookCart bc) {
        bc.clear();
    }
    
    public void addBook(final Book book) {
        this.bookDao.insert(book);
    }
}
