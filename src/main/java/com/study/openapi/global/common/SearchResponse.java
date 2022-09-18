package com.study.openapi.global.common;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SearchResponse<T> implements Serializable {
    private Meta meta;
    private List<T> documents;
}
