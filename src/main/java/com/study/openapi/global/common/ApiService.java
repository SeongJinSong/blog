package com.study.openapi.global.common;

import com.study.openapi.global.exception.OpenApiCallException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService <T> {
    private final WebClientRequester webClientRequester;
    public SearchResponse call(String host, HttpServletRequest httpservletRequest, SearchRequest request) {
        System.out.println(host+httpservletRequest.getRequestURI()+httpservletRequest.getQueryString());
        return Optional.ofNullable(webClientRequester.getWebClient(
                host+"/"+httpservletRequest.getRequestURI()+"?"+
                        //TODO 인크딩으로 인해 공백이 들어간 검색결과가 달라서 임시로 조합함
                        "query="+request.getQuery()+"&sort="+request.getSort()+"&page="+request.getPage()+"&size="+request.getSize())
                        .bodyToMono(SearchResponse.class).block())
                        .orElseThrow(OpenApiCallException::new);
    }

}
