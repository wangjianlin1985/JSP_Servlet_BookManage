// 
// 
// 

package com.sa.dao;

import java.util.List;
import com.sa.web.Page;
import com.sa.web.CriteriaBook;
import com.sa.domain.Book;

public interface BookDao
{
    Book getBook(String p0);
    
    void insert(Book p0);
    
    void delete(String p0);
    
    void update(Book p0);
    
    Page<Book> getBookPage(CriteriaBook p0);
    
    long getTotalBookNumber(CriteriaBook p0);
    
    List<Book> getBookPageList(CriteriaBook p0, int p1);
}
