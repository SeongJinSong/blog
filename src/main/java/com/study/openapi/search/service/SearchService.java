package com.study.openapi.search.service;

import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.search.contoller.dto.SearchRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    void saveRequest(SearchRequest request);
    Page<SearchRank> getPopularSearchWord(Pageable pageable);
}
