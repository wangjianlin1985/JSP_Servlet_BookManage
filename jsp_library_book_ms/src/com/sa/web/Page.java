// 
// 
// 

package com.sa.web;

import java.util.List;

public class Page<T>
{
    private int pageNo;
    private List<T> list;
    private int pageSize;
    private long totalItemNumber;
    
    public Page(final int pageNo) {
        this.pageSize = 10;
        this.pageNo = pageNo;
    }
    
    public int getPageNo() {
        if (this.pageNo > this.getTotalPageNumber()) {
            this.pageNo = this.getTotalPageNumber();
        }
        if (this.pageNo <= 0) {
            this.pageNo = 1;
        }
        return this.pageNo;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setList(final List<T> list) {
        this.list = list;
    }
    
    public List<T> getList() {
        return this.list;
    }
    
    public int getTotalPageNumber() {
        int totalPageNumber = (int)(this.totalItemNumber / this.pageSize);
        if (this.totalItemNumber % this.pageSize != 0L) {
            ++totalPageNumber;
        }
        return totalPageNumber;
    }
    
    public void setTotalItemNumber(final int totalItemNumber) {
        this.totalItemNumber = totalItemNumber;
    }
    
    public boolean isHasNext() {
        return this.getPageNo() < this.getTotalPageNumber();
    }
    
    public boolean isHasPrev() {
        return this.getPageNo() != 1;
    }
    
    public int getPrevPage() {
        if (this.isHasPrev()) {
            return this.getPageNo() - 1;
        }
        return this.getPageNo();
    }
    
    public int getNextPage() {
        if (this.isHasNext()) {
            return this.getPageNo() + 1;
        }
        return this.getPageNo();
    }
}
