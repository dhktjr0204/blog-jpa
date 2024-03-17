package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.dto.AddCommentRequest;
import com.estsoft.blogjpa.domain.dto.AllCommentResponse;
import com.estsoft.blogjpa.domain.dto.CommentDto;
import com.estsoft.blogjpa.domain.entity.Article;
import com.estsoft.blogjpa.domain.entity.Comment;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.estsoft.blogjpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public Comment findComment(Long articleId, Long id) {
        return commentRepository.findByArticleIdAndId(articleId, id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment AddCommentRequest(Long articleId, AddCommentRequest request) {
        Article article = blogRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        Comment comment = request.toEntity(article);
        return commentRepository.save(comment);
    }

    public AllCommentResponse findAllComments(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId)
                .orElseThrow(IllegalArgumentException::new);

        List<CommentDto> commentDtoList=new ArrayList<>();

        for (Comment comment : comments) {
            commentDtoList.add(comment.toEntity());
        }

        return new AllCommentResponse(comments.get(0).getArticle(), commentDtoList);
    }
}
