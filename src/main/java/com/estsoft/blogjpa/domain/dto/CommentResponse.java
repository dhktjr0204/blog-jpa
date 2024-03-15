package com.estsoft.blogjpa.domain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private String body;
    private LocalDateTime createdAt;
}
