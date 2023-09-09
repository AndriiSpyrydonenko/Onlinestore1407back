package com.svitsmachnogo.api.dto;

import java.util.List;

public class PageDataDTO<T> {

    private List<T> data;

    private long total;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
