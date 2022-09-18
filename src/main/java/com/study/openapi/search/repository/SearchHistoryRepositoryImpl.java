package com.study.openapi.search.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.openapi.search.contoller.dto.SearchRank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

import static com.study.openapi.search.domain.QSearchHistory.searchHistory;

@Slf4j
public class SearchHistoryRepositoryImpl implements SearchHistoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public SearchHistoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<SearchRank> findTopSearchHistory(Pageable pageable) {
        JPAQuery<SearchRank> content = queryFactory.select(
                        Projections.fields(SearchRank.class,
                                searchHistory.searchRequest.query,
                                searchHistory.searchRequest.query.count().as("count"))
                )
                .from(searchHistory)
                .groupBy(searchHistory.searchRequest.query)
                .orderBy(searchHistory.searchRequest.query.count().desc());
        long totalCnt = content.fetch().size();
        long offset = pageable.getOffset();
        long limit = pageable.getPageSize()==0?10:pageable.getPageSize();
        return PageableExecutionUtils.getPage(content.offset(offset).limit(limit).fetch(), pageable, () -> totalCnt);
    }
}
