package com.study.openapi.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class SearchRequest {
    @NotBlank
    private String query;
    private String sort;
    private Integer page;
    private Integer size;
}
