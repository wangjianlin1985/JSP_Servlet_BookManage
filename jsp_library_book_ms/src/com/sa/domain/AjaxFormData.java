// 
// 
// 

package com.sa.domain;

public class AjaxFormData
{
    private String college;
    private String major;
    private String classes;
    
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
    
    public AjaxFormData(final String college, final String major, final String classes) {
        this.college = college;
        this.major = major;
        this.classes = classes;
    }
    
    public AjaxFormData() {
    }
}
