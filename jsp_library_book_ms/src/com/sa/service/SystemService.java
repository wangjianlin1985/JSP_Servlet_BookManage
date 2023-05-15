// 
// 
// 

package com.sa.service;

import com.sa.domain.System;
import com.sa.dao.impl.SystemDaoImpl;
import com.sa.dao.SystemDao;

public class SystemService
{
    private SystemDao systemDao;
    
    public SystemService() {
        this.systemDao = new SystemDaoImpl();
    }
    
    public System getAdmin(final String id) {
        return this.systemDao.getAdmin(id);
    }
    
    public void changePwd(final String userId, final String newPwd) {
        this.systemDao.changePwd(userId, newPwd);
    }
}
