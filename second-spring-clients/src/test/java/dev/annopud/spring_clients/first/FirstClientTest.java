package dev.annopud.spring_clients.first;

import dev.annopud.second_spring_clients.first.FirstClient;
import dev.annopud.second_spring_clients.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstClientTest {

    @Autowired
    private FirstClient firstClient;

    @Test
    void findById() {
        User byId = firstClient.findById(1);
        System.out.println(byId);
    }

}