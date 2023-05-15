// 
// 
// 

package com.sa.web;

import com.sa.domain.Book;

public class CriteriaBook
{
    private Book book;
    private int pageNo;
    
    public Book getBook() {
        return this.book;
    }
    
    public void setBook(final Book book) {
        this.book = book;
    }
    
    public int getPageNo() {
        return this.pageNo;
    }
    
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
    }
    
    public CriteriaBook(final Book book, final int pageNo) {
        this.book = book;
        this.pageNo = pageNo;
    }
    
    public CriteriaBook() {
    }
}
