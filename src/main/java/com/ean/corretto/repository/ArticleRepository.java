package com.ean.corretto.repository;

import com.ean.corretto.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
}
