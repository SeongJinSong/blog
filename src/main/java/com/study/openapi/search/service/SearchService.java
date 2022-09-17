package com.study.openapi.search.service;

import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.search.domain.SearchHistory;
import com.study.openapi.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;
    @Transactional
    public void saveRequest(SearchRequest request) {
        log.info("SearchService.saveRequest");
        searchRepository.save(SearchHistory.builder().searchRequest(request).build());
    }
}
