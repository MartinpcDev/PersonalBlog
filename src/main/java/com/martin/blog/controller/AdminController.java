package com.martin.blog.controller;

import com.martin.blog.service.IArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @GetMapping("/edit/{id}")
  public String getEditPage(@PathVariable Integer id, Model model) {
    model.addAttribute("article", articleService.getArticleById(id));
    return "editArticle";
  }

  @PostMapping("/edited")
  public String getEditedPage(
      @RequestParam String id,
      @RequestParam String title,
      @RequestParam String fecha,
      @RequestParam String content,
      Model model) {
    articleService.updateArticle(Integer.parseInt(id), title, content, fecha);
    model.addAttribute("articles", articleService.getAllArticles());
    return "admin";
  }

  @GetMapping("/delete/{id}")
  public String getDeletedPage(@PathVariable Integer id, Model model) {
    articleService.deleteArticle(id);
    model.addAttribute("articles", articleService.getAllArticles());
    return "redirect:/admin";
  }
}
