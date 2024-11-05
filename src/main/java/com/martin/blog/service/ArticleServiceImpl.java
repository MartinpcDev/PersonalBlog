package com.martin.blog.service;

import com.martin.blog.model.Article;
import jakarta.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements IArticleService {

  private List<Article> articles = new ArrayList<>();
  private final LocalConsumer localConsumer;

  @Autowired
  public ArticleServiceImpl(LocalConsumer localConsumer) {
    this.localConsumer = localConsumer;
  }

  @PostConstruct
  public void init() {
    this.articles = loadArticles();
  }

  private List<Article> loadArticles() {
    try {
      return localConsumer.readData();
    } catch (Exception e) {
      System.out.println("Error al cargar los artículos: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  private void saveData() {
    localConsumer.writeListData(articles);
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

  @Override
  public void updateArticle(Integer id, String title, String content, String date) {
    Optional<Article> articleExists = articles.stream()
        .filter(a -> Objects.equals(a.getId(), id))
        .findFirst();
    if (articleExists.isPresent()) {
      Article updateArticle = articleExists.get();
      updateArticle.setTitle(title);
      updateArticle.setContent(content);
      try {
        updateArticle.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").parse(date));
      } catch (ParseException e) {
        System.out.println(e.getMessage());
      }
      saveData();
    } else {
      System.out.println("El article no existe");
    }
  }

  @Override
  public void deleteArticle(Integer id) {
    articles.removeIf(a -> Objects.equals(a.getId(), id));
    saveData();
  }

  @Override
  public void saveArticle(String title, String content) {
    int id = articles.size() + 1;
    int finalId = id;
    Optional<Article> articleExists = articles.stream()
        .filter(a -> Objects.equals(a.getId(), finalId)).findFirst();
    if (articleExists.isPresent()) {
      id++;
    }
    Article article = new Article(id, title, content);
    articles.add(article);
    saveData();
  }
}
