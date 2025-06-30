package dev.annopud.spring_clients.user;

import dev.annopud.second_spring_clients.user.UserClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Test
    void findAll() {
        StepVerifier.create(userClient.findAll())
                .expectNextCount(10)
                .verifyComplete();
    }

}