package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.dto.AddArticleRequest;
import com.estsoft.blogjpa.domain.entity.Article;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.estsoft.blogjpa.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ac;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUp(){
        blogRepository.deleteAll();
        this.mockMvc= MockMvcBuilders.webAppContextSetup(ac).build();
    }

    @Test
    public void addArticle() throws Exception {
        //given: 저장하고싶은 블로그 정보
        AddArticleRequest request=new AddArticleRequest("제목", "내용");
        // object -> json
        String json = objectMapper.writeValueAsString(request);

        //when : POST /api/articles
        ResultActions resultActions = mockMvc.perform(post("/api/articles").content(json)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        //then : 저장이 잘 되었는지 확인, HttpStatusCode 201 검증 {"title": "제목"", "content":"내용"}
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("content").value(request.getContent()));

    }

    @Test
    public void findAllArticles() throws Exception {
        Article article1=new Article("title1","content1");
        Article article2=new Article("title2","content2");

        //given : blogRepository.save
        List<Article> articleList= new ArrayList<>();
        articleList.add(article1);
        articleList.add(article2);

        blogRepository.saveAll(articleList);

        //when : GET /api/articles
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(articleList.get(0).getTitle()))
                .andExpect(jsonPath("$[0].content").value(articleList.get(0).getContent()))
                .andExpect(jsonPath("$[1].title").value(articleList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].content").value(articleList.get(1).getContent()));
    }

    @Test
    public void deleteOneArticle() throws Exception {
        //given: 삭제할 대상 데이터 sava
        Article article=blogRepository.save(new Article("title","content"));
        Long id=article.getId();

        //when : DELETE /api/articles/{id}
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        //then
        resultActions.andExpect(status().isOk());
        Optional<Article> deleteArticle = blogRepository.findById(id);
        Assertions.assertFalse(deleteArticle.isPresent());
    }
}