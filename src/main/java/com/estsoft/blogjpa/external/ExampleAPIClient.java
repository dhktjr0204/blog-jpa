package com.estsoft.blogjpa.external;

import com.estsoft.blogjpa.domain.dto.AddArticleRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * RestTemplate : HTTP 통신을 위한 도구
 * 외부 도메인으로부터 데이터를 가져오거나 전송할 때 사용
 */
@Slf4j
@Component
public class ExampleAPIClient {
    private final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    public List<AddArticleRequest> fetchDataFromApi() {
        RestTemplate restTemplate = new RestTemplate(); // RestTemplate 객체 생성

        // API로 GET 요청을 보냈고, ResponseEntity<String>으로 json 응답 받음
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(API_URL, List.class);

        List<AddArticleRequest> requests=new ArrayList<>();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {  //HTTP status code 200 체크
            List<LinkedHashMap<String,Object>> list=responseEntity.getBody();
            for(LinkedHashMap<String,Object> map:list){
                String title =(String) map.get("title");
                String content=(String) map.get("body");

                requests.add(new AddArticleRequest(title,content));
            }
            return requests;
        } else {
            log.error("Failed to fetch data from API. Status code: " + responseEntity.getStatusCodeValue());
            return null;
        }
    }
}