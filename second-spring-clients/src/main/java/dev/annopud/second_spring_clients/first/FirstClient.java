package dev.annopud.second_spring_clients.first;

import dev.annopud.second_spring_clients.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface FirstClient {

    @GetExchange("/users")
    String findAll();

    @GetExchange("/users/{id}")
    User findById(@PathVariable int id);

}
