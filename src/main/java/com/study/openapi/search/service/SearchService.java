package com.study.openapi.search.service;

import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.search.domain.SearchHistory;
import com.study.openapi.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;
    @Transactional
    public void saveRequest(SearchRequest request) {
        searchRepository.save(SearchHistory.builder().searchRequest(request).build());
    }
}
