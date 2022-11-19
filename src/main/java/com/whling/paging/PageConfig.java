package com.whling.paging;

public class PageConfig {

    private int currentPage;

    private int pageSize;


    public PageConfig(int currentPage, int pageSize) {
        if (currentPage <= 0) {
            throw new IllegalArgumentException(String.format("currentPage:%d <= 0", currentPage));
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException(String.format("pageSize:%d <= 0", pageSize));
        }
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int toOffset() {
        return (currentPage - 1) * pageSize;
    }
}
