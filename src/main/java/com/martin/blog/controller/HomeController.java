package com.martin.blog.controller;

import com.martin.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

  private final IArticleService articleService;

  @Autowired
  public HomeController(IArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping("/home")
  public String getHomePage(Model model) {
    model.addAttribute("articles", articleService.getAllArticles());
    return "home";
  }


  @GetMapping("/article/{id}")
  public String getArticle(@PathVariable Integer id, Model model) {
    model.addAttribute("article", articleService.getArticleById(id));
    return "article";
  }

  @GetMapping("/error")
  public String getErrorPage() {
    return "error";
  }
}
