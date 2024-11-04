package com.martin.blog.service;

import com.martin.blog.model.Article;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    articles.add(new Article(1, "First title",
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
    articles.add(new Article(2, "Second title",
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
    articles.add(new Article(3, "Third title",
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
    articles.add(new Article(4, "Fourth title",
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
    articles.add(new Article(5, "Fifth title",
        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
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
    } else {
      System.out.println("El article no existe");
    }
  }

  @Override
  public void deleteArticle(Integer id) {
    articles.removeIf(a -> Objects.equals(a.getId(), id));
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
  }
}
