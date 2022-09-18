package com.study.openapi.search.service;

import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.search.contoller.dto.SearchRank;
import com.study.openapi.search.domain.SearchHistory;
import com.study.openapi.search.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchHistoryComp1Service implements SearchHistoryService {
    private final SearchHistoryRepository searchRepository;
    @Override
    public void saveRequest(SearchRequest request) {
        searchRepository.save(SearchHistory.builder().searchRequest(request).build());
    }

    @Override
    public Page<SearchRank> getPopularSearchWord(Pageable pageable) {
        return searchRepository.findTopSearchHistory(pageable);
    }
}
