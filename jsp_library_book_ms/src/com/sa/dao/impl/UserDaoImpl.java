// 
// 
// 

package com.sa.dao.impl;

import java.util.List;
import com.sa.util.CriteriaUserUtils;
import com.sa.web.Page;
import com.sa.web.CriteriaUser;
import com.sa.dao.UserDao;
import com.sa.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao
{
    @Override
    public User getUser(final String id) {
        final String sql = "SELECT id,name,college,major,classes,password,imgPath FROM user_info WHERE id = ?";
        return this.query(sql, id);
    }
    
    @Override
    public void insert(final User user) {
        final String sql = "INSERT INTO user_info(id,name,college,major,classes,password,imgpath) VALUES(?,?,?,?,?,?,?)";
        this.update(sql, user.getId(), user.getName(), user.getCollege(), user.getMajor(), user.getClasses(), user.getId(), user.getImgPath());
    }
    
    @Override
    public void delete(final String id) {
        final String sql = "DELETE FROM user_info WHERE id = ?";
        this.update(sql, id);
    }
    
    @Override
    public void update(final User user) {
        final String sql = "UPDATE user_info SET name = ?,college = ?,major = ?,classes = ?,imgPath = ?,password = ? WHERE id = ?";
        this.update(sql, user.getName(), user.getCollege(), user.getMajor(), user.getClasses(), user.getImgPath(), user.getPassword(), user.getId());
    }
    
    @Override
    public Page<User> getPage(final CriteriaUser cu) {
        final Page page = new Page(cu.getPageNo());
        page.setTotalItemNumber((int)this.getTotalUserNumber(cu));
        cu.setPageNo(page.getPageNo());
        page.setList(this.getUserPageList(cu, 10));
        return (Page<User>)page;
    }
    
    @Override
    public long getTotalUserNumber(final CriteriaUser cu) {
        final String sql = "SELECT count(*) FROM user_info WHERE" + CriteriaUserUtils.getUserFilter(cu).toString();
        return this.getSingleVal(sql, new Object[0]);
    }
    
    @Override
    public List<User> getUserPageList(final CriteriaUser cu, final int pageSize) {
        final String sql = "SELECT id,name,college,major,classes,password FROM user_info WHERE" + CriteriaUserUtils.getUserFilter(cu).toString() + " LIMIT ?,?";
        return this.queryForList(sql, (cu.getPageNo() - 1) * pageSize, pageSize);
    }
    
    @Override
    public void changePwd(final String userId, final String newPwd) {
        final String sql = "UPDATE user_info SET password = ? WHERE id = ?";
        this.update(sql, newPwd, userId);
    }
}
