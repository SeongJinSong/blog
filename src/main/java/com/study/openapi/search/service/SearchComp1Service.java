package com.study.openapi.search.service;

import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.search.contoller.dto.SearchRank;
import com.study.openapi.search.domain.SearchHistory;
import com.study.openapi.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchComp1Service implements SearchService{
    private final SearchRepository searchRepository;
    @Override
    public void saveRequest(SearchRequest request) {
        searchRepository.save(SearchHistory.builder().searchRequest(request).build());
    }

    @Override
    public Page<SearchRank> getPopularSearchWord(Pageable pageable) {
        return searchRepository.findTopSearchHistory(pageable);
    }
}
