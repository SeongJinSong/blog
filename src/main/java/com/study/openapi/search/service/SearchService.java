package com.study.openapi.search.service;

import com.study.openapi.search.contents.Blog;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 검색과 관련된 동작을 정의한 인터페이스
 */
public interface SearchService {
    SearchResponse<Blog> getBlogList(
            HttpServletRequest httpservletRequest, @Valid SearchRequest request);
}
