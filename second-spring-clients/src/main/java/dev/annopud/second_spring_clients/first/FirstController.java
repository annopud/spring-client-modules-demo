package dev.annopud.second_spring_clients.first;

import dev.annopud.second_spring_clients.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/first")
public class FirstController {
    private final FirstService firstService;

    public FirstController(FirstService firstService) {
        this.firstService = firstService;
    }

    @GetMapping("")
    public String listServices() {
        return firstService.getServices();
    }

    // Example endpoint to get all users
    @GetMapping("/users")
    public String getAllUsers() {
        return "Through http client --> " + firstService.getAllUsers();
    }

    @GetMapping("/users2")
    public String getAllUsers2() {
        return "Through feign client --> " + firstService.getAllUsersUsingFeign();
    }

    // Example endpoint to get a user by ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return firstService.getUserById(id);
    }

    @GetMapping("/users2/{id}")
    public User getUserById2(@PathVariable int id) {
        return firstService.findUserAsynchronously2(id);
    }

    @GetMapping("/users3/{id}")
    public User getUserById3(@PathVariable int id) {
        return firstService.findUserAsynchronously3(id);
    }

    @GetMapping("/users4/{id}")
    public CompletableFuture<User> getUserById4(@PathVariable int id) {
        return firstService.findUserAsync4(id);
    }
}
