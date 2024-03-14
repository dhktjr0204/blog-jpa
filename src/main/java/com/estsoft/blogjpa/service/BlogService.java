package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.dto.AddArticleRequest;
import com.estsoft.blogjpa.domain.entity.Article;
import com.estsoft.blogjpa.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Article saveArticle(AddArticleRequest request){
        Article article = request.toEntity();
        return blogRepository.save(article);
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id).orElse(new Article());
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, AddArticleRequest request){
        Article article = findById(id);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    @Transactional
    public Article updateTitle(Long id, AddArticleRequest request){
        Article article=findById(id);
        blogRepository.updateTitle(id, request.getTitle());
        return article;
    }
}
