package com.study.openapi.global.common;


import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SearchResponse<T>  {
    private Meta meta;
    private List<T> documents;
}
