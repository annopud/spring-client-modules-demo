package dev.annopud.spring_clients.first;

import dev.annopud.spring_clients.user.User;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class FirstController {
    private final DiscoveryClient discoveryClient;
    private final FirstClient firstClient;
    private final FirstFeignClient firstFeignClient;

    public FirstController(
        DiscoveryClient discoveryClient,
        FirstClient firstClient,
        FirstFeignClient firstFeignClient
    ) {
        this.discoveryClient = discoveryClient;
        this.firstClient = firstClient;
        this.firstFeignClient = firstFeignClient;
    }

    @GetMapping("")
    public String index() {
        return String.join(", ", discoveryClient.getServices());
    }

    // Example endpoint to get all users
    @GetMapping("/users")
    public String getAllUsers() {
        return "Through http client --> " + firstClient.findAll();
    }

    @GetMapping("/users2")
    public String getAllUsers2() {
        return "Through feign client --> " + firstFeignClient.findAll();
    }

    // Example endpoint to get a user by ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return firstClient.findById(id);
    }
}
