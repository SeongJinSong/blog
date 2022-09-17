package com.study.openapi.global.common;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter //setter가 있어야 argumentResolver에서 객체로 매핑 가능한거 같다.
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@ToString
public class SearchRequest {
    @NotBlank
    private String query;
    private String sort;
    private Integer page;
    private Integer size;
}
