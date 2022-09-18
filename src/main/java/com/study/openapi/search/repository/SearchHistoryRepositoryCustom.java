package com.study.openapi.search.repository;

import com.study.openapi.search.contoller.dto.SearchRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchHistoryRepositoryCustom {
    Page<SearchRank> findTopSearchHistory(Pageable pageable);
}
