// 
// 
// 

package com.sa.domain;

import java.sql.Date;

public class BookBorrow
{
    private Integer id;
    private String userId;
    private String name;
    private String classes;
    private Date borTime;
    private Date retTime;
    private Date relTime;
    
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
    
    public void setName(final String userName) {
        this.name = userName;
    }
    
    public String getClasses() {
        return this.classes;
    }
    
    public void setClasses(final String classes) {
        this.classes = classes;
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
    
    public BookBorrow(final Integer id, final String userId, final String userName, final String classes, final Date broTime, final Date retTime, final Date relTime) {
        this.id = id;
        this.userId = userId;
        this.name = userName;
        this.classes = classes;
        this.borTime = broTime;
        this.retTime = retTime;
        this.relTime = relTime;
    }
    
    public BookBorrow() {
    }
    
    @Override
    public String toString() {
        return "BookBorrow[id=" + this.id + "]";
    }
}
