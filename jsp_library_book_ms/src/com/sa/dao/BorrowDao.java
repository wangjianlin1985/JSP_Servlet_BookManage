// 
// 
// 

package com.sa.dao;

import com.sa.domain.Book;
import java.util.Collection;
import com.sa.domain.Borrow;
import java.util.List;

public interface BorrowDao
{
    List<Borrow> getBorrowRecord(String p0);
    
    long getCurBorrowCount(String p0);
    
    void borrowBook(String p0, Collection<Book> p1);
    
    long overBookCount(String p0);
    
    long hasBook(String p0);
    
    void updateBookQuantityForBorrow(Collection<Book> p0);
    
    long isAlreadyBorrow(String p0, String p1);
}
