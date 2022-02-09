package pl.sages.consumenewsapi.api.services;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sages.consumenewsapi.api.config.ApiConfigurationProperties;
import pl.sages.consumenewsapi.api.domain.Article;
import pl.sages.consumenewsapi.api.domain.Articles;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final RestTemplate restTemplate;
    private final ApiConfigurationProperties apiConfigurationProperties;

    public ArticleServiceImpl(RestTemplate restTemplate, ApiConfigurationProperties apiConfigurationProperties) {
        this.restTemplate = restTemplate;
        this.apiConfigurationProperties = apiConfigurationProperties;
    }

    @Override
    public List<Article> getArticles() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(apiConfigurationProperties.getUrl())
                .queryParam("country", apiConfigurationProperties.getCountry())
                .queryParam("category", apiConfigurationProperties.getCategory())
                .queryParam("apiKey", apiConfigurationProperties.getKey());

        Articles articles = restTemplate.getForObject(uriBuilder.toUriString(), Articles.class);
        return articles.getArticles();
    }

    @Override
    public void saveArticlesToFile(List<Article> articles, String fileName) {
        fileName = fileName.isBlank() ? "Articles" : fileName;
        try {
            FileUtils.writeLines(new File(String.format("src/main/resources/articles/%s.txt", fileName)), articles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
