// 
// 
// 

package com.sa.web;

import com.sa.domain.User;

public class CriteriaUser
{
    private User user;
    private int pageNo;
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public int getPageNo() {
        return this.pageNo;
    }
    
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
    }
    
    public CriteriaUser(final User user, final int pageNo) {
        this.user = user;
        this.pageNo = pageNo;
    }
    
    public CriteriaUser() {
    }
}
