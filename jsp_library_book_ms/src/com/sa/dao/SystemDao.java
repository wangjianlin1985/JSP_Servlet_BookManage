// 
// 
// 

package com.sa.dao;

import com.sa.domain.System;

public interface SystemDao
{
    System getAdmin(String p0);
    
    void changePwd(String p0, String p1);
}
