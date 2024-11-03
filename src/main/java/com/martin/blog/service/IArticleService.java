package com.martin.blog.service;

import com.martin.blog.model.Article;
import java.util.List;

public interface IArticleService {

  List<Article> getAllArticles();

  Article getArticleById(Integer id);
}
