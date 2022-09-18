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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
//TODO SearchService 범용적으로 처리하기 위해 인터페이스로 변경
public class SearchService {
    private final SearchRepository searchRepository;
    @Transactional
    public void saveRequest(SearchRequest request) {
        log.info("SearchService.saveRequest");
        searchRepository.save(SearchHistory.builder().searchRequest(request).build());
    }

    public Page<SearchRank> getPopularSearchWord(Pageable pageable) {
        return searchRepository.findTopSearchHistory(pageable);
    }
}
