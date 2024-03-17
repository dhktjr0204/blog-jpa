package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByArticleIdAndId(Long articleId, Long id);
    Optional<List<Comment>> findByArticleId(Long id);
}