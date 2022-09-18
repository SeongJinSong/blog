package com.study.openapi.search.service;

import com.study.openapi.documents.Blog;
import com.study.openapi.global.base.ResponseWrapper;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.search.contoller.dto.SearchRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 검색과 관련된 동작을 정의한 인터페이스
 */
public interface SearchService {
    SearchResponse<Blog> getBlogList(
            HttpServletRequest httpservletRequest, @Valid SearchRequest request);
}
