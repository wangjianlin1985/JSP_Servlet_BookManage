// 
// 
// 

package com.sa.dao;

import java.util.List;

public interface Dao<T>
{
    long insert(String p0, Object... p1);
    
    void update(String p0, Object... p1);
    
    T query(String p0, Object... p1);
    
    List<T> queryForList(String p0, Object... p1);
    
     <V> V getSingleVal(String p0, Object... p1);
    
    void batch(String p0, Object[]... p1);
}
