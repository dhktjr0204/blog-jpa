package com.estsoft.blogjpa.domain.dto;

import com.estsoft.blogjpa.domain.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AllCommentResponse {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;

    public AllCommentResponse(Article article, List<CommentResponse> comments) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.comments = comments;
    }
}
