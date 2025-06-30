package dev.annopud.second_spring_clients.post;

import dev.annopud.first_spring_clients.post.PostClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostClientTest {

    @Autowired
    private PostClient postClient;

    @Test
    void findAll() {
        var posts = postClient.findAll();
        assertEquals(100, posts.size());
    }
}