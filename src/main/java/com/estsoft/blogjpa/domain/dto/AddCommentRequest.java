package com.estsoft.blogjpa.domain.dto;

import com.estsoft.blogjpa.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {
    private String body;

    public Comment toEntity(Long id) {
        return Comment.builder()
                .articleId(id)
                .body(body)
                .build();
    }
}
