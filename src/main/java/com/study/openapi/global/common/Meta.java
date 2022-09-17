package com.study.openapi.global.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    @JsonProperty("total_count")
    private int totalCount;
    @JsonProperty("pageable_count")
    private int pageableCount;
    @JsonProperty("is_end")
    private boolean isEnd;
}