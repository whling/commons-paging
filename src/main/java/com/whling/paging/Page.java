package com.whling.paging;

import java.util.Collections;
import java.util.List;

public class Page<T> {

    private PageInfo pageInfo;

    private List<T> data;

    public Page(PageInfo pageInfo, List<T> data) {
        this.pageInfo = pageInfo;
        this.data = data;
    }

    public Page(int totalItem, int currentPage, int pageSize) {
        this(totalItem, new PageConfig(currentPage, pageSize));
    }

    public Page(int totalItem, PageConfig pageConfig) {
        if (totalItem < 0) {
            throw new IllegalArgumentException("totalItem < 0");
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalItem(totalItem);
        if (totalItem == 0) {
            pageInfo.setTotalPage(0);
        } else {
            int pageSize = pageConfig.getPageSize();
            pageInfo.setTotalPage((totalItem - 1) / pageSize + 1);
        }
        pageInfo.setCurrentPage(pageConfig.getCurrentPage());
        pageInfo.setPageSize(pageConfig.getPageSize());
        this.pageInfo = pageInfo;
    }

    public static <T> Page<T> emptyPage(int size, PageConfig pageConfig) {
        Page<T> page = new Page<>(size, pageConfig);
        page.setData(Collections.emptyList());
        return page;
    }

    public static <T> Page<T> emptyPage(PageConfig pageConfig) {
        return emptyPage(0, pageConfig);
    }

    public static <T> Page<T> fromAllItems(PageConfig pageConfig, List<T> wholeItems) {
        if (wholeItems == null || wholeItems.size() <= 0) {
            return emptyPage(pageConfig);
        }

        int startIndex = pageConfig.toOffset();
        if (startIndex >= wholeItems.size()) {
            return emptyPage(wholeItems.size(), pageConfig);
        }

        Page<T> page = new Page<>(wholeItems.size(), pageConfig);
        page.setData(
                wholeItems.subList(startIndex,
                        Math.min(wholeItems.size(), startIndex + pageConfig.getPageSize())));
        return page;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
