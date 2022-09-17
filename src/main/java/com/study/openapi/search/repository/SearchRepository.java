package com.study.openapi.search.repository;

import com.study.openapi.search.domain.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<SearchHistory, Long> {
}
