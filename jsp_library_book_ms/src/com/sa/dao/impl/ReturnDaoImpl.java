// 
// 
// 

package com.sa.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.sa.domain.Book;
import java.util.Collection;
import com.sa.dao.ReturnDao;
import com.sa.domain.Borrow;

public class ReturnDaoImpl extends BaseDao<Borrow> implements ReturnDao
{
    @Override
    public long isOverFine(final String userId, final String bookId) {
        final String sql = "select to_days(now()) - to_days(retTime) from borrow_info where userId = ? AND bookId = ? AND relTime is null;";
        final Long num = this.getSingleVal(sql, userId, bookId);
        return Long.parseLong(new StringBuilder(String.valueOf(num)).toString());
    }
    
    @Override
    public void returnBook(final String userId, final Collection<Book> books, final Map<String, Float> overFine) {
        final String sql = "UPDATE borrow_info SET relTime = now(),overFine = ? WHERE userId = ? AND bookId = ? AND relTime is null";
        final Object[][] params = new Object[books.size()][3];
        final List<Book> bookItems = new ArrayList<Book>(books);
        for (int i = 0; i < bookItems.size(); ++i) {
            params[i][0] = overFine.get(bookItems.get(i).getBookId());
            params[i][1] = userId;
            params[i][2] = bookItems.get(i).getBookId();
        }
        this.batch(sql, params);
    }
    
    @Override
    public void updateBookQuantityForReturn(final Collection<Book> books) {
        final String sql = "UPDATE book_info SET aviQuantity = aviQuantity + 1 WHERE bookId = ?";
        final Object[][] params = new Object[books.size()][1];
        final List<Book> bookItems = new ArrayList<Book>(books);
        for (int i = 0; i < bookItems.size(); ++i) {
            params[i][0] = bookItems.get(i).getBookId();
        }
        this.batch(sql, params);
    }
    
    @Override
    public long isUserBorrowBook(final String userId, final String bookId) {
        final String sql = "SELECT COUNT(*) FROM borrow_info WHERE userId = ? AND bookId = ? AND relTime is null";
        return this.getSingleVal(sql, userId, bookId);
    }
    
    @Override
    public void fine(final String userid) {
        final String sql = "UPDATE borrow_info SET overFine = 0 - overFine WHERE overFine > 0 AND userid = ?";
        this.update(sql, userid);
    }
}
