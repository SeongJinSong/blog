package com.study.openapi.global.common;


import java.util.List;

public class SearchResponse<T>  {
    private Meta meta;
    private List<T> documents;
}
