// 
// 
// 

package com.sa.dao;

import java.util.List;
import com.sa.web.Page;
import com.sa.web.CriteriaUser;
import com.sa.domain.User;

public interface UserDao
{
    User getUser(String p0);
    
    void insert(User p0);
    
    void delete(String p0);
    
    void update(User p0);
    
    Page<User> getPage(CriteriaUser p0);
    
    long getTotalUserNumber(CriteriaUser p0);
    
    List<User> getUserPageList(CriteriaUser p0, int p1);
    
    void changePwd(String p0, String p1);
}
