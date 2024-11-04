package com.martin.blog.controller;

import com.martin.blog.service.IArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


  private final IArticleService articleService;

  public AdminController(IArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping
  public String getAdminPage(Model model) {
    model.addAttribute("articles", articleService.getAllArticles());
    return "admin";
  }

  @GetMapping("/edit")
  public String getEditPage() {
    return "editArticle";
  }
}
