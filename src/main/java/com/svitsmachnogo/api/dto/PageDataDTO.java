package com.svitsmachnogo.api.dto;

import java.util.List;

public class PageDataDTO<T> {

    private List<T> data;

    private int pageCount;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
