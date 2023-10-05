package com.svitsmachnogo.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDataDTO<T> {

    private List<T> data;

    private int pageCount;

}
