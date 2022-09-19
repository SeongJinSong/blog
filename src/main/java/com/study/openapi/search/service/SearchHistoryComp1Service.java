package com.study.openapi.search.service;

import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.search.contoller.dto.SearchRank;
import com.study.openapi.search.domain.SearchHistory;
import com.study.openapi.search.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Host : dapi.kakao.com
 * Authorization: KakaoAK ${REST_API_KEY:9ccac738a217f2aa9d006c01900809cc}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchHistoryComp1Service implements SearchHistoryService {
    private final SearchHistoryRepository searchRepository;
    private final Executor threadPoolTaskExecutor;
    @Override
    public void saveRequest(SearchRequest request) {
        CompletableFuture.runAsync(() -> searchRepository.save(SearchHistory.builder().searchRequest(request).build()), threadPoolTaskExecutor);
    }

    @Override
    public Page<SearchRank> getPopularSearchWord(Pageable pageable) {
        return searchRepository.findTopSearchHistory(pageable);
    }
}
