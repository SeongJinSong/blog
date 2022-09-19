package com.study.openapi.search.service;

import com.study.openapi.global.common.ApiService;
import com.study.openapi.global.common.SearchRequest;
import com.study.openapi.global.common.SearchResponse;
import com.study.openapi.redis.service.QueryCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchComp1Service implements SearchService{
    private final SearchHistoryService searchHistoryService;
    private final QueryCountService queryCountService;
    private final SearchRankService searchRankService;
    private final ApiService apiService;
    String host = "https://dapi.kakao.com";

    @Override
    public SearchResponse getBlogList(HttpServletRequest httpservletRequest, SearchRequest request) {
        log.info("request : {}", request);
        searchHistoryService.saveRequest(request);

        String key = getComp1ApiRedisKey(httpservletRequest.getRequestURI(), httpservletRequest.getQueryString());
        /*
            TODO
             1. @Cacheable을 적용하여 분기 삭제
             2. API자체를 캐싱하는건 Client단에서 어울릴만한 일이다. reactQuery가 그런비슷한 역할을 하기도한다.
             서버단에서는 뭔가 서버단에서 어울릴만한 캐싱 알고리즘을 찾아보아야 할 것 같다.
         */
        SearchResponse response = queryCountService.getApiResultCache(key);
        if(response==null){
            //api 호출
            log.info("@@@@@@@ api 직접 호출 key={}", key);
            response = apiService.call(host, httpservletRequest, request);
            //레디스에 api 검색결과 저장
            queryCountService.saveApiResultCache(key, response);
        }

        //레디스에 카운트 설정
        //TODO LongAdder, Accumulator 적용 -> zset 적용
        queryCountService.inCreateCount(request.getQuery());
        //TODO redisconfig에서 redis가 뜰때, 집계함수를 통해 count 집계
        //TODO 레디스에 카운트 센것 주기적으로 db에 저장 혹은 정합성 검사

        //ZSET을 이용하여 처리
        searchRankService.addSearchRequest(request);
        /* TODO
            조회와 삽입이 분리되면 동시성 문제가 발생할 수 있어 lua-script 기반으로 돌리는 방법도 찾아보자자
        */
        return response;
    }
    private String getComp1ApiRedisKey(String uri, String queryString) {
        return host+"/"+ uri +"?" + queryString;
    }
}
