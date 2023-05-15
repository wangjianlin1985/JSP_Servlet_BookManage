// 
// 
// 

package com.sa.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.sa.domain.Book;
import java.util.Collection;
import com.sa.dao.impl.ReturnDaoImpl;
import com.sa.dao.ReturnDao;

public class ReturnService
{
    private ReturnDao returnDao;
    
    public ReturnService() {
        this.returnDao = new ReturnDaoImpl();
    }
    
    public String returnBook(final String userId, final Collection<Book> books, final float fine, final Map<String, Float> overFine) {
        final String msg = this.checkOverFineAndIsUserBorrowBook(userId, books, overFine, fine);
        this.returnDao.returnBook(userId, books, overFine);
        this.returnDao.updateBookQuantityForReturn(books);
        return msg;
    }
    
    public String checkOverFineAndIsUserBorrowBook(final String userId, final Collection<Book> books, final Map<String, Float> overFine, final float fine) {
        final StringBuffer msg = new StringBuffer();
        final List<Book> bookItems = new ArrayList<Book>(books);
        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < bookItems.size(); ++i) {
            final boolean checked = this.isUserBorrowBook(userId, bookItems.get(i).getBookId());
            if (!checked) {
                bookItems.remove(i);
                flag2 = true;
                msg.append(String.valueOf(bookItems.get(i).getBookName()) + ",");
            }
        }
        if (flag2) {
            msg.append("\u4e0d\u662f\u60a8\u7684\u5728\u501f\u56fe\u4e66\uff01");
        }
        for (int i = 0; i < bookItems.size(); ++i) {
            final int day_dif = (int)this.returnDao.isOverFine(userId, bookItems.get(i).getBookId());
            if (day_dif > 0) {
                flag1 = true;
                msg.append(String.valueOf(bookItems.get(i).getBookName()) + ",");
            }
            overFine.put(bookItems.get(i).getBookId(), (day_dif > 0) ? (day_dif * fine) : 0.0f);
        }
        if (flag1) {
            msg.append("\u5b58\u5728\u8d85\u671f\u7f5a\u6b3e,\u8bf7\u5230\u670d\u52a1\u53f0\u7f34\u7eb3\uff01");
        }
        return msg.toString();
    }
    
    public boolean isUserBorrowBook(final String userId, final String bookId) {
        return this.returnDao.isUserBorrowBook(userId, bookId) > 0L;
    }
    
    public void fine(final String userid) {
        this.returnDao.fine(userid);
    }
}
