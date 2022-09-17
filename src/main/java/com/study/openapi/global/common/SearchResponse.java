package com.study.openapi.global.common;


import lombok.ToString;

import java.util.List;

@ToString
public class SearchResponse<T>  {
    private Meta meta;
    private List<T> documents;
}
