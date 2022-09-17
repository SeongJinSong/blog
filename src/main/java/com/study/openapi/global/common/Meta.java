package com.study.openapi.global.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Meta {
    @JsonProperty("total_count")
    private int totalCount;
    @JsonProperty("pageable_count")
    private int pageableCount;
    @JsonProperty("is_end")
    private boolean isEnd;
}
