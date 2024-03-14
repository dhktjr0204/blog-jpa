package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.dto.AddArticleRequest;
import com.estsoft.blogjpa.domain.dto.ArticleResponse;
import com.estsoft.blogjpa.domain.entity.Article;
import com.estsoft.blogjpa.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<Article> articles=blogService.findAll();
        List<ArticleResponse> responseList = articles.stream().map(Article::toResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id){
        Article article=blogService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(article.toResponse());
    }

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request){
        Article article = blogService.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article.toResponse());
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/api/articles/{id}")
//    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
//                                                 @RequestBody AddArticleRequest request){
//        Article article = blogService.update(id, request);
//
//        return ResponseEntity.ok(article);
//    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                    @RequestBody AddArticleRequest request){
        Article updated=blogService.updateTitle(id, request);

        return ResponseEntity.ok(updated);
    }
}
