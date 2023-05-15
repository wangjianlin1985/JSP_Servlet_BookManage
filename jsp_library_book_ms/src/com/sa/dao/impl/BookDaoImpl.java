// 
// 
// 

package com.sa.dao.impl;

import java.util.List;
import com.sa.util.CriteriaUserUtils;
import com.sa.web.Page;
import com.sa.web.CriteriaBook;
import com.sa.dao.BookDao;
import com.sa.domain.Book;

public class BookDaoImpl extends BaseDao<Book> implements BookDao
{
    @Override
    public Book getBook(final String id) {
        final String sql = "SELECT bookId,bookName,type,isbn,author,press,pubTime,allQuantity,aviQuantity,imgPath FROM book_info WHERE bookId = ?";
        return this.query(sql, id);
    }
    
    @Override
    public void insert(final Book book) {
        final String sql = "INSERT INTO book_info(bookName,type,isbn,author,press,pubTime,allQuantity,aviQuantity,imgPath) VALUES(?,?,?,?,?,?,?,?,?)";
        this.update(sql, book.getBookName(), book.getType(), book.getIsbn(), book.getAuthor(), book.getPress(), book.getPubTime(), book.getAllQuantity(), book.getAviQuantity(), book.getImgPath());
    }
    
    @Override
    public void delete(final String id) {
        final String sql = "DELETE FROM book_info WHERE bookId = ?";
        this.update(sql, id);
    }
    
    @Override
    public void update(final Book book) {
        final String sql = "UPDATE book_info SET bookName = ?,type = ?,isbn = ?,author = ?,press = ?,pubTime = ?,allQuantity = ?,aviQuantity = ?,imgPath = ? WHERE bookId = ?";
        this.update(sql, book.getBookName(), book.getType(), book.getIsbn(), book.getAuthor(), book.getPress(), book.getPubTime(), book.getAllQuantity(), book.getAviQuantity(), book.getImgPath(), book.getBookId());
    }
    
    @Override
    public Page<Book> getBookPage(final CriteriaBook cb) {
        final Page page = new Page(cb.getPageNo());
        page.setTotalItemNumber((int)this.getTotalBookNumber(cb));
        cb.setPageNo(page.getPageNo());
        page.setList(this.getBookPageList(cb, 10));
        return (Page<Book>)page;
    }
    
    @Override
    public long getTotalBookNumber(final CriteriaBook cb) {
        final String sql = "SELECT COUNT(*) FROM book_info WHERE" + CriteriaUserUtils.getBookFilter(cb).toString();
        return this.getSingleVal(sql, new Object[0]);
    }
    
    @Override
    public List<Book> getBookPageList(final CriteriaBook cb, final int pageSize) {
        final String sql = "SELECT bookId,bookName,type,isbn,author,press,pubTime,allQuantity,aviQuantity,imgPath FROM book_info WHERE" + CriteriaUserUtils.getBookFilter(cb).toString() + " LIMIT ?,?";
        return this.queryForList(sql, (cb.getPageNo() - 1) * pageSize, pageSize);
    }
}
