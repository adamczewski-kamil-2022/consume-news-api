package pl.sages.consumenewsapi.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sages.consumenewsapi.api.domain.Article;
import pl.sages.consumenewsapi.api.services.ArticleService;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "index";
    }

    @PostMapping("/saveArticles")
    public String saveArticles(@ModelAttribute("fileName") String fileName) {
        List<Article> articles = articleService.getArticles();
        articleService.saveArticlesToFile(articles, fileName);
        return "redirect:/";
    }
}
