// 
// 
// 

package com.sa.dao.impl;

import java.util.List;
import com.sa.dao.BookBorrowDao;
import com.sa.domain.BookBorrow;

public class BookBorrowDaoImpl extends BaseDao<BookBorrow> implements BookBorrowDao
{
    @Override
    public List<BookBorrow> getBookBorrow(final String bookId) {
        final String sql = "SELECT b.id,userId,name,classes,borTime,retTime,relTime FROM user_info u LEFT JOIN borrow_info b ON u.id = b.userId WHERE bookId = ? ORDER BY borTime DESC";
        return this.queryForList(sql, bookId);
    }
}
