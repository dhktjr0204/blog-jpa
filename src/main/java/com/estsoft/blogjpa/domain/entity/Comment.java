package com.estsoft.blogjpa.domain.entity;

import com.estsoft.blogjpa.domain.dto.CommentDto;
import com.estsoft.blogjpa.domain.dto.CommentResponse;
import com.estsoft.blogjpa.repository.CommentRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    private Article article;

    @Column(name = "body", nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Comment(Article article, String body){
        this.article=article;
        this.body=body;
    }

    public CommentDto toEntity(){
        return CommentDto.builder()
                .id(id)
                .body(body)
                .createdAt(createdAt)
                .build();
    }

    public CommentResponse toResponse(){
        return CommentResponse.builder()
                .id(id)
                .articleId(article.getId())
                .body(body)
                .createdAt(createdAt)
                .build();
    }
}
