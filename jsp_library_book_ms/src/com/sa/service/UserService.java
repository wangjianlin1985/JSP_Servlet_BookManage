// 
// 
// 

package com.sa.service;

import com.sa.domain.AjaxFormData;
import java.util.List;
import com.sa.domain.User;
import com.sa.web.Page;
import com.sa.web.CriteriaUser;
import com.sa.dao.impl.AjaxDaoImpl;
import com.sa.dao.impl.UserDaoImpl;
import com.sa.dao.AjaxDao;
import com.sa.dao.UserDao;

public class UserService
{
    private UserDao userDao;
    private AjaxDao ajaxDao;
    
    public UserService() {
        this.userDao = new UserDaoImpl();
        this.ajaxDao = new AjaxDaoImpl();
    }
    
    public Page<User> getUserPage(final CriteriaUser cu) {
        return this.userDao.getPage(cu);
    }
    
    public User getUser(final String id) {
        return this.userDao.getUser(id);
    }
    
    public void updateUser(final User user) {
        this.userDao.update(user);
    }
    
    public void changePwd(final String userId, final String newPwd) {
        this.userDao.changePwd(userId, newPwd);
    }
    
    public void addUser(final User user) {
        this.userDao.insert(user);
    }
    
    public List<AjaxFormData> getColleges() {
        final List<AjaxFormData> colleges = this.ajaxDao.getCollege();
        return colleges;
    }
    
    public List<AjaxFormData> getMajors(final String college) {
        return this.ajaxDao.getMajor(college);
    }
    
    public List<AjaxFormData> getClasses(final String major) {
        return this.ajaxDao.getClasses(major);
    }
}
