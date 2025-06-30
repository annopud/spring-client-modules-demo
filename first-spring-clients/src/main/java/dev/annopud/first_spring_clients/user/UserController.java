package dev.annopud.first_spring_clients.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserRestClient userRestClient;
    // Alternatively, you could use UserHttpClient instead of UserRestClient
    private final UserHttpClient userHttpClient;
    private final DiscoveryClient discoveryClient;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserRestClient userRestClient, UserHttpClient userHttpClient, DiscoveryClient discoveryClient) {
        this.userRestClient = userRestClient;
        this.userHttpClient = userHttpClient;
        this.discoveryClient = discoveryClient;
    }

    @JsonIgnoreProperties(ignoreUnknown = false)
    @Data
    public static class TestRequest extends TestRequestBase {
        private String second;
        private String third;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = false)
    public static class TestRequestBase {
        private String first;
    }

    @PostMapping("/update/test")
    public ResponseEntity<String> testParam(@RequestBody TestRequest param) {
        return ResponseEntity.ok("RM Update All Endpoint");
    }

    @GetMapping("")
    public String findAll() {
        logger.info("Fetching all users through http client");
        return "List services by spring clients: " + String.join(", ", discoveryClient.getServices());
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userRestClient.findById(id);
    }

    @GetMapping("/http")
    public List<User> findAllHttp() {
        return userHttpClient.findAll();
    }

    @GetMapping("/http/{id}")
    public User findByIdHttp(@PathVariable int id) {
        return userHttpClient.findById(id);
    }
}
