package pl.sages.consumenewsapi.api.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sages.consumenewsapi.api.domain.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    ArticleService articleService;

    @AfterAll
    static void cleanUp() throws IOException {
        FileUtils.deleteDirectory(new File("src/main/resources/articles"));
    }

    // according to NewsAPI documentation (https://newsapi.org/docs/endpoints/top-headlines) default number of results per page
    // (request) is 20 -> can be changed with request parameter pageSize (maximum value is 100)
    @Test()
    void shouldFetch20Articles() {
        // given
        // when
        List<Article> articles = articleService.getArticles();

        // then
        assertTrue(articles.size() <= 20);
        assertTrue(articles.size() > 0);
    }

    @Test
    void shouldSaveArticlesToGivenFile() {
        // given
        List<Article> articles = articleService.getArticles();
        String fileName = "FileName";

        // when
        articleService.saveArticlesToFile(articles, fileName);

        // then
        assertTrue(Files.exists(Paths.get("src/main/resources/articles/FileName.txt")));
    }

    @Test
    void shouldSaveArticlesToFileWithDefaultName() {
        // given
        List<Article> articles = articleService.getArticles();
        String fileName = "";

        // when
        articleService.saveArticlesToFile(articles, fileName);

        // then
        assertTrue(Files.exists(Paths.get("src/main/resources/articles/Articles.txt")));
    }
}