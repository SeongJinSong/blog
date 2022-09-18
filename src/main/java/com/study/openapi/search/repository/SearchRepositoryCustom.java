package com.study.openapi.search.repository;

import com.study.openapi.search.contoller.dto.SearchRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchRepositoryCustom {
    Page<SearchRank> findTop10SearchHistory(Pageable pageable);
    List<SearchRank> findTop10SearchHistory();
}
