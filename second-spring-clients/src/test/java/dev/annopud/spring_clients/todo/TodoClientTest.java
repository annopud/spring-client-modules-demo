package dev.annopud.spring_clients.todo;

import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoClientTest {

    @Autowired
    private TodoClient todoClient;

    @Test
    void findAll() {
        List<Todo> todos = todoClient.findAll();
        Assertions.assertThat(todos).hasSize(200);
    }

    @Test
    void findAllTest() {
        AtomicBoolean b = new AtomicBoolean(false);
        CompletableFuture.runAsync(() -> b.set(true));
        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(
            b::get
        );
    }

}