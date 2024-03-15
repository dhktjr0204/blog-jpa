package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.dto.AddCommentRequest;
import com.estsoft.blogjpa.domain.dto.AllCommentResponse;
import com.estsoft.blogjpa.domain.entity.Article;
import com.estsoft.blogjpa.domain.entity.Comment;
import com.estsoft.blogjpa.service.BlogService;
import com.estsoft.blogjpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final BlogService blogService;

    @GetMapping("/{articleId}/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long articleId
            , @PathVariable Long commentId){
        Comment comment = commentService.findComment(articleId,commentId);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<Comment> saveComment(@PathVariable Long articleId
            , @RequestBody AddCommentRequest request){
        Comment comment = commentService.AddCommentRequest(articleId, request);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<AllCommentResponse> getAllComments(@PathVariable Long articleId){
        Article article = blogService.findById(articleId);
        List<Comment> allComments = commentService.findAllComments(articleId);

        AllCommentResponse response=new AllCommentResponse(article, allComments);

        return ResponseEntity.ok(response);
    }
}
