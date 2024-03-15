package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.dto.AddCommentRequest;
import com.estsoft.blogjpa.domain.dto.AllCommentResponse;
import com.estsoft.blogjpa.domain.entity.Comment;
import com.estsoft.blogjpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment findComment(Long articleId, Long id){
        return commentRepository.findByArticleIdAndId(articleId,id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment AddCommentRequest(Long articleId, AddCommentRequest request){
        Comment comment = request.toEntity(articleId);
        return commentRepository.save(comment);
    }

    public List<Comment> findAllComments(Long articleId){
        return commentRepository.findByArticleId(articleId);
    }
}
