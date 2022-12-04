package com.eval.kubernetessandbox.api;

import com.eval.kubernetessandbox.doman.bookmark.Bookmark;
import com.eval.kubernetessandbox.doman.bookmark.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class BookmarkControllerTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private BookmarkRepository bookmarkRepository;


    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        List<Bookmark> bookmarks = List.of(
                new Bookmark(null, "How To Remove Docker Containers, Images, Volumes, and Networks", "https://linuxize.com/post/how-to-remove-docker-images-containers-volumes-and-networks", Instant.now()),
                new Bookmark(null, "All You Need To Know About Unit Testing with Spring Boot", "https://reflectoring.io/unit-testing-spring-boot", Instant.now()),
                new Bookmark(null, "Spring Boot Security Jwt Authentication", "https://www.devglan.com/spring-security/spring-boot-jwt-auth", Instant.now()),
                new Bookmark(null, "A categorized list of all Java and JVM features since JDK 8", "https://advancedweb.hu/2019/02/19/post_java_8", Instant.now()),
                new Bookmark(null, "Flyway and jOOQ for Unbeatable SQL Development Productivity", "https://blog.jooq.org/2014/06/25/flyway-and-jooq-for-unbeatable-sql-development-productivity", Instant.now()),
                new Bookmark(null, "Java Microservices: A Practical Guide", "https://www.marcobehler.com/guides/java-microservices-a-practical-guide", Instant.now()),
                new Bookmark(null, "Spring Boot integration with p6spy, datasource-proxy, flexy-pool and spring-cloud-sleuth", "https://github.com/gavlyukovskiy/spring-boot-data-source-decorator", Instant.now()),
                new Bookmark(null, "Build a JavaScript Command Line Interface (CLI) with Node.js", "https://www.sitepoint.com/javascript-command-line-interface-cli-node-js", Instant.now()),
                new Bookmark(null, "How to teach Git", "https://rachelcarmena.github.io/2018/12/12/how-to-teach-git.html", Instant.now()),
                new Bookmark(null, "Achieving framework-independent zen with the power of interfaces and hexagonal architecture", "https://rskupnik.github.io/framework-independence-with-hexagonal-architecture", Instant.now()),
                new Bookmark(null, "Deserialize Immutable Objects with Jackson", "https://www.baeldung.com/jackson-deserialize-immutable-objects", Instant.now()),
                new Bookmark(null, "SpringBoot Integration Testing using TestContainers Starter", "https://sivalabs.in/2020/02/spring-boot-integration-testing-using-testcontainers-starter", Instant.now()));
        bookmarkRepository.saveAll(bookmarks);
    }


    @ParameterizedTest
    @CsvSource({
            "1, 10, 2, 0, true, false, true, false",
            "2, 2,  2, 1, false, true, false, true"
    })
    void shouldGetBookmarks(int pageNumber, int totalElements, int totalPages, int currentPage, boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page=" + pageNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", equalTo(hasPrevious)));
    }

}