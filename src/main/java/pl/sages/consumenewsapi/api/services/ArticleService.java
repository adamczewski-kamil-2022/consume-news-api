package pl.sages.consumenewsapi.api.services;

import pl.sages.consumenewsapi.api.domain.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getArticles();

    void saveArticlesToFile(List<Article> articles, String fileName);

}
