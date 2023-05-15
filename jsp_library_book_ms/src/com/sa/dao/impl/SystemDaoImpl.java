// 
// 
// 

package com.sa.dao.impl;

import com.sa.dao.SystemDao;
import com.sa.domain.System;

public class SystemDaoImpl extends BaseDao<System> implements SystemDao
{
    @Override
    public System getAdmin(final String id) {
        final String sql = "SELECT id,name,password FROM sys_info WHERE id = ?";
        return this.query(sql, id);
    }
    
    @Override
    public void changePwd(final String id, final String newPwd) {
        final String sql = "UPDATE sys_info SET password = ? WHERE id = ?";
        this.update(sql, newPwd, id);
    }
}
