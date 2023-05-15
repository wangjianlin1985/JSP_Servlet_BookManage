// 
// 
// 

package com.sa.domain;

public class User
{
    private String id;
    private String name;
    private String college;
    private String major;
    private String classes;
    private String password;
    private String imgPath;
    
    public String getImgPath() {
        return this.imgPath;
    }
    
    public void setImgPath(final String imgPath) {
        this.imgPath = imgPath;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getCollege() {
        return this.college;
    }
    
    public void setCollege(final String college) {
        this.college = college;
    }
    
    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(final String major) {
        this.major = major;
    }
    
    public String getClasses() {
        return this.classes;
    }
    
    public void setClasses(final String classes) {
        this.classes = classes;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public User(final String id, final String name, final String college, final String major, final String classes, final String password, final String imgPath) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.major = major;
        this.classes = classes;
        this.password = password;
        this.imgPath = imgPath;
    }
    
    public User() {
    }
    
    @Override
    public String toString() {
        return "User[id" + this.id + ",name" + this.name + ",college" + this.college + ",major=" + this.major + ",classes=" + this.classes + ",password=" + this.password + ",imgPath=" + this.imgPath + "]";
    }
}
