// 
// 
// 

package com.sa.dao.impl;

import java.sql.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.sa.domain.Book;
import java.util.Collection;
import java.util.List;
import com.sa.dao.BorrowDao;
import com.sa.domain.Borrow;

public class BorrowDaoImpl extends BaseDao<Borrow> implements BorrowDao
{
    @Override
    public List<Borrow> getBorrowRecord(final String id) {
        final String sql = "SELECT b.id,userId,name,b.bookId,bookName,borTime,retTime,relTime,overFine FROM borrow_info b left join user_info u on b.userId = u.id left join book_info bk ON b.bookid = bk.bookid WHERE u.id = ? ORDER BY borTime DESC";
        return this.queryForList(sql, id);
    }
    
    @Override
    public long getCurBorrowCount(final String userId) {
        final String sql = "SELECT COUNT(*) FROM borrow_info WHERE userid = ? AND relTime is null";
        return this.getSingleVal(sql, userId);
    }
    
    @Override
    public void borrowBook(final String userId, final Collection<Book> books) {
        final String sql = "INSERT INTO borrow_info(userId,bookId,borTime,retTime) VALUES(?,?,?,?);";
        final Object[][] params = new Object[books.size()][4];
        final List<Book> bookItems = new ArrayList<Book>(books);
        Date two_Month_later = null;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar c = Calendar.getInstance();
        c.add(5, 60);
        two_Month_later = new Date(c.getTime().getTime());
        for (int i = 0; i < bookItems.size(); ++i) {
            params[i][0] = userId;
            params[i][1] = bookItems.get(i).getBookId();
            params[i][2] = new Date(new java.util.Date().getTime());
            params[i][3] = two_Month_later;
        }
        this.batch(sql, params);
    }
    
    @Override
    public long overBookCount(final String userId) {
        final String sql = "select count(*) from borrow_info WHERE to_days(now()) > to_days(retTime) AND relTime is null AND userId = ?;";
        return this.getSingleVal(sql, userId);
    }
    
    @Override
    public long hasBook(final String bookId) {
        final String sql = "SELECT COUNT(*) FROM book_info WHERE aviQuantity > 0 AND bookId = ?";
        return this.getSingleVal(sql, bookId);
    }
    
    @Override
    public void updateBookQuantityForBorrow(final Collection<Book> books) {
        final String sql = "UPDATE book_info SET aviQuantity = aviQuantity - 1 WHERE bookId = ?";
        final Object[][] params = new Object[books.size()][1];
        final List<Book> bookItems = new ArrayList<Book>(books);
        for (int i = 0; i < bookItems.size(); ++i) {
            params[i][0] = bookItems.get(i).getBookId();
        }
        this.batch(sql, params);
    }
    
    @Override
    public long isAlreadyBorrow(final String userId, final String bookId) {
        final String sql = "SELECT COUNT(*) FROM borrow_info WHERE userId = ? AND bookId = ? AND relTime is NULL";
        return this.getSingleVal(sql, userId, bookId);
    }
}
