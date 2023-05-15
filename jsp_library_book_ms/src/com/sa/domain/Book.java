// 
// 
// 

package com.sa.domain;

import java.sql.Date;

public class Book
{
    private String bookId;
    private String bookName;
    private String type;
    private String isbn;
    private String author;
    private String press;
    private Date pubTime;
    private Integer allQuantity;
    private Integer aviQuantity;
    private String imgPath;
    
    public String getBookId() {
        return this.bookId;
    }
    
    public void setBookId(final String bookId) {
        this.bookId = bookId;
    }
    
    public String getBookName() {
        return this.bookName;
    }
    
    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(final String author) {
        this.author = author;
    }
    
    public String getPress() {
        return this.press;
    }
    
    public void setPress(final String press) {
        this.press = press;
    }
    
    public Date getPubTime() {
        return this.pubTime;
    }
    
    public void setPubTime(final Date pubTime) {
        this.pubTime = pubTime;
    }
    
    public Integer getAllQuantity() {
        return this.allQuantity;
    }
    
    public void setAllQuantity(final Integer allQuantity) {
        this.allQuantity = allQuantity;
    }
    
    public Integer getAviQuantity() {
        return this.aviQuantity;
    }
    
    public void setAviQuantity(final Integer aviQuantity) {
        this.aviQuantity = aviQuantity;
    }
    
    public String getImgPath() {
        return this.imgPath;
    }
    
    public void setImgPath(final String imgPath) {
        this.imgPath = imgPath;
    }
    
    public Book(final String bookId, final String bookName, final String type, final String isbn, final String author, final String press, final Date pubTime, final Integer allQuantity, final Integer aviQuantity, final String imgPath) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.type = type;
        this.isbn = isbn;
        this.author = author;
        this.press = press;
        this.pubTime = pubTime;
        this.allQuantity = allQuantity;
        this.aviQuantity = aviQuantity;
        this.imgPath = imgPath;
    }
    
    public Book() {
    }
    
    @Override
    public String toString() {
        return "Book [bookid=" + this.bookId + ",bookname=" + this.bookName + ",type=" + this.type + ",isbn=" + this.isbn + "allquantity" + this.allQuantity + ",aviQuantity=" + this.aviQuantity + ",imgPath=" + this.imgPath + ",press=" + this.press + ",author=" + this.author + "]";
    }
}
