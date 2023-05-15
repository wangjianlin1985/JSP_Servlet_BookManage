// 
// 
// 

package com.sa.domain;

public class System
{
    private Integer id;
    private String name;
    private String password;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public System(final Integer id, final String name, final String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    public System() {
    }
    
    @Override
    public String toString() {
        return "System [id=" + this.id + ",name=" + this.name + ",password=" + this.password + "]";
    }
}
