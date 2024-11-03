package com.martin.blog.model;

import java.util.Date;

public class Article {

  private Integer id;
  private String title;
  private String content;
  private Date createdAt;

  public Article(Integer id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdAt = new Date();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
