package com.martin.blog.service;

import com.martin.blog.model.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements IArticleService {

  private List<Article> articles = new ArrayList<>();

  public ArticleServiceImpl() {
    this.articles = loadArticles();
  }

  private List<Article> loadArticles() {
    articles.add(new Article(1, "First title", "First content"));
    articles.add(new Article(2, "Second title", "Second content"));
    articles.add(new Article(3, "Third title", "Third content"));
    articles.add(new Article(4, "Fourth title", "Fourth content"));
    articles.add(new Article(5, "Fifth title", "Fifth content"));
    return articles;
  }


  @Override
  public List<Article> getAllArticles() {
    return articles;
  }

  @Override
  public Article getArticleById(Integer id) {
    Optional<Article> article = articles.stream()
        .filter(a -> Objects.equals(a.getId(), id)).findFirst();

    return article.orElse(null);
  }
}
