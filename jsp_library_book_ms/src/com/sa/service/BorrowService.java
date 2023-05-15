// 
// 
// 

package com.sa.service;

import java.util.ArrayList;
import com.sa.domain.Book;
import java.util.Collection;
import com.sa.domain.Borrow;
import java.util.List;
import com.sa.dao.impl.BorrowDaoImpl;
import com.sa.dao.BorrowDao;

public class BorrowService
{
    private BorrowDao borrowDao;
    
    public BorrowService() {
        this.borrowDao = new BorrowDaoImpl();
    }
    
    public List<Borrow> getUserBorrowRec(final String id) {
        return this.borrowDao.getBorrowRecord(id);
    }
    
    public int getCurBorrowCount(final String userId) {
        return (int)this.borrowDao.getCurBorrowCount(userId);
    }
    
    public String borrowBook(final String userId, final Collection<Book> books) {
        final StringBuffer msg = new StringBuffer();
        final int overBookCount = (int)this.overBook(userId);
        if (overBookCount > 0) {
            msg.append("\u60a8\u5b58\u5728" + overBookCount + "\u672c\u8d85\u671f\u56fe\u4e66\uff0c\u5f53\u524d\u4e0d\u80fd\u7ee7\u7eed\u501f\u9605\u3002");
            return msg.toString();
        }
        msg.append(this.bookQuantityChecked(books));
        this.borrowDao.borrowBook(userId, books);
        this.borrowDao.updateBookQuantityForBorrow(books);
        return msg.toString();
    }
    
    public long overBook(final String userId) {
        return this.borrowDao.overBookCount(userId);
    }
    
    public String bookQuantityChecked(final Collection<Book> books) {
        final List<Book> allBooks = new ArrayList<Book>(books);
        final StringBuffer msg = new StringBuffer();
        boolean flag = true;
        for (int i = 0; i < allBooks.size(); ++i) {
            if (this.borrowDao.hasBook(allBooks.get(i).getBookId()) <= 0L) {
                msg.append(String.valueOf(allBooks.get(i).getBookName()) + ",");
                allBooks.remove(i);
                flag = false;
            }
        }
        if (!flag) {
            msg.append("\u5c1a\u5728\u501f\u9605\uff0c\u9986\u85cf\u4e0d\u8db3\uff01");
        }
        return msg.toString();
    }
    
    public long isAlreadyBorrow(final String userId, final String bookId) {
        return this.borrowDao.isAlreadyBorrow(userId, bookId);
    }
}
