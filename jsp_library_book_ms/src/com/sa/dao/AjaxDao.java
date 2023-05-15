// 
// 
// 

package com.sa.dao;

import com.sa.domain.AjaxFormData;
import java.util.List;

public interface AjaxDao
{
    List<AjaxFormData> getCollege();
    
    void testCommit2git();
    
    List<AjaxFormData> getMajor(String p0);
    
    List<AjaxFormData> getClasses(String p0);
}
