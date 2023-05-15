// 
// 
// 

package com.sa.domain;

import java.sql.Date;

public class Borrow
{
    private Integer id;
    private String userId;
    private String name;
    private String bookName;
    private Integer bookId;
    private Date borTime;
    private Date retTime;
    private Date relTime;
    private Float overFine;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getBookName() {
        return this.bookName;
    }
    
    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }
    
    public Integer getBookId() {
        return this.bookId;
    }
    
    public void setBookId(final Integer bookId) {
        this.bookId = bookId;
    }
    
    public Date getBorTime() {
        return this.borTime;
    }
    
    public void setBorTime(final Date borTime) {
        this.borTime = borTime;
    }
    
    public Date getRetTime() {
        return this.retTime;
    }
    
    public void setRetTime(final Date retTime) {
        this.retTime = retTime;
    }
    
    public Date getRelTime() {
        return this.relTime;
    }
    
    public void setRelTime(final Date relTime) {
        this.relTime = relTime;
    }
    
    public Float getOverFine() {
        return this.overFine;
    }
    
    public void setOverFine(final Float overFine) {
        this.overFine = overFine;
    }
    
    public Borrow(final Integer id, final String userId, final String userName, final String bookName, final Integer bookId, final Date borTime, final Date retTime, final Date relTime, final Float overFine) {
        this.id = id;
        this.userId = userId;
        this.name = userName;
        this.bookName = bookName;
        this.bookId = bookId;
        this.borTime = borTime;
        this.retTime = retTime;
        this.relTime = relTime;
        this.overFine = overFine;
    }
    
    public Borrow() {
    }
    
    @Override
    public String toString() {
        return "Borrow [id=" + this.id + ",bookName=" + this.bookName + ",name=" + this.name + ",userId=" + this.userId + "bookId=" + this.bookId + ",bortime=" + this.borTime + ",retTime=" + this.relTime + ",relTime=" + this.relTime + ",overFine" + this.overFine + "]";
    }
}
