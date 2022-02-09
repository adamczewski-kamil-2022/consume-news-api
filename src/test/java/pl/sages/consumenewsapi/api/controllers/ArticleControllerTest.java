package pl.sages.consumenewsapi.api.controllers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sages.consumenewsapi.api.services.ArticleService;

import java.io.File;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ArticleControllerTest {

    @Autowired
    ArticleController articleController;

    @Autowired
    ArticleService articleService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @AfterAll
    static void cleanUp() throws IOException {
        FileUtils.deleteDirectory(new File("src/main/resources/articles"));
    }

    @Test
    void listArticlesTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("articles"))
                .andExpect(view().name("index"));
    }

    @Test
    void saveArticlesToFileTest() throws Exception {
        mockMvc.perform(post("/saveArticles")
                        .param("fileName", "SavedArticles"))
                .andExpect(status().is3xxRedirection());
    }
}