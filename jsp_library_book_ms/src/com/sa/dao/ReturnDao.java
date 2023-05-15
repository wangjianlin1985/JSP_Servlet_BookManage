// 
// 
// 

package com.sa.dao;

import java.util.Map;
import com.sa.domain.Book;
import java.util.Collection;

public interface ReturnDao
{
    long isOverFine(String p0, String p1);
    
    void returnBook(String p0, Collection<Book> p1, Map<String, Float> p2);
    
    void updateBookQuantityForReturn(Collection<Book> p0);
    
    long isUserBorrowBook(String p0, String p1);
    
    void fine(String p0);
}
