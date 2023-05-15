// 
// 
// 

package com.sa.dao.impl;

import java.util.List;
import com.sa.dao.AjaxDao;
import com.sa.domain.AjaxFormData;

public class AjaxDaoImpl extends BaseDao<AjaxFormData> implements AjaxDao
{
    @Override
    public List<AjaxFormData> getCollege() {
        final String sql = "SELECT DISTINCT college FROM user_info";
        return this.queryForList(sql, new Object[0]);
    }
    
    @Override
    public List<AjaxFormData> getMajor(final String college) {
        final String sql = "SELECT DISTINCT major FROM user_info WHERE college = ?";
        return this.queryForList(sql, college);
    }
    
    @Override
    public List<AjaxFormData> getClasses(final String major) {
        final String sql = "SELECT DISTINCT classes FROM user_info WHERE major = ?";
        return this.queryForList(sql, major);
    }
    
    @Override
    public void testCommit2git() {
    }
}
