package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);

    // JPQL = Java Persistence Query Language
    @Modifying
    @Query("update Article set title = :title where id = :id")
    void updateTitle(Long id, String title);
}
