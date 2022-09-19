package com.study.openapi.search.service;

import com.study.openapi.search.contents.Blog;
import com.study.openapi.search.dto.SearchResponse;

/**
 * 검색과 관련된 동작을 정의한 인터페이스
 */
public interface SearchService {
    SearchResponse<Blog> getContentsList(
            String uri, String queryString);
}
