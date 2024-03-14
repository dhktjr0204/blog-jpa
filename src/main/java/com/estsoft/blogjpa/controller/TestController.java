package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.dto.AddArticleRequest;
import com.estsoft.blogjpa.external.ExampleAPIClient;
import com.estsoft.blogjpa.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final ExampleAPIClient apiClient;
    private final BlogService blogService;

    @GetMapping("/client/test")
    public String clientTest(){
        List<AddArticleRequest> requests = apiClient.fetchDataFromApi();
        for(AddArticleRequest request: requests){
            blogService.saveArticle(request);
        }
        return "ok";
    }
}
