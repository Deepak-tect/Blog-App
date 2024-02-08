package com.blog.blogingapp.Payloads;

import java.util.*;
public class ApiPageResponse<T> {
    private List<T> contents;
    private int pageNumber;
    private int pageSize;
    private long TotalElements;
    private int TotalPages;
    private boolean isLastPage;
    public ApiPageResponse() {
    }
    public ApiPageResponse(List<T> contents, int pageNumber, int pageSize, long totalElements, int totalPages,
            boolean isLastPage) {
        this.contents = contents;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        TotalElements = totalElements;
        TotalPages = totalPages;
        this.isLastPage = isLastPage;
    }
    public List<T> getContents() {
        return contents;
    }
    public void setContents(List<T> contents) {
        this.contents = contents;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotalElements() {
        return TotalElements;
    }
    public void setTotalElements(long totalElements) {
        TotalElements = totalElements;
    }
    public int getTotalPages() {
        return TotalPages;
    }
    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }
    public boolean isLastPage() {
        return isLastPage;
    }
    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }
    

}
