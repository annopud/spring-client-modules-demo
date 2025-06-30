package dev.annopud.second_spring_clients.first;

import dev.annopud.second_spring_clients.config.CompletableFutureExecutor;
import dev.annopud.second_spring_clients.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class FirstService {
    private static final Logger logger = LoggerFactory.getLogger(FirstService.class);
    private final DiscoveryClient discoveryClient;
    private final FirstClient firstClient;
    private final FirstFeignClient firstFeignClient;
    private final FirstRepository firstRepository;
    private final CompletableFutureExecutor completableFutureExecutor;

    public FirstService(
        DiscoveryClient discoveryClient,
        FirstClient firstClient,
        FirstFeignClient firstFeignClient,
        FirstRepository firstRepository,
        CompletableFutureExecutor completableFutureExecutor
    ) {
        this.discoveryClient = discoveryClient;
        this.firstClient = firstClient;
        this.firstFeignClient = firstFeignClient;
        this.completableFutureExecutor = completableFutureExecutor;
        this.firstRepository = firstRepository;
    }

    // Method to get the list of services
    public String getServices() {
        logger.info("Fetching services from DiscoveryClient");
        return String.join(", ", discoveryClient.getServices());
    }

    // Method to get all users
    public String getAllUsers() {
        logger.info("Fetching all users using FirstClient");
        return firstClient.findAll();
    }

    // Method to get all users using Feign client
    public String getAllUsersUsingFeign() {
        logger.info("Fetching all users using FirstFeignClient");
        return firstFeignClient.findAll();
    }

    // Method to get a user by ID
    public User getUserById(int id) {
        logger.info("Fetching user with ID: {}", id);
        return firstClient.findById(id);
    }

    public User findUserAsynchronously2(Integer id) {
        logger.info("findUserAsynchronously2: Looking up {}", id);
//        findUserAsync(id); // Trigger the asynchronous call
        User user = findUserAsync(id).join();
        logger.info("findUserAsynchronously2: Found user: {}", user);
        return user;
    }

    public User findUserAsynchronously3(Integer id) {
        logger.info("findUserAsynchronously3: Looking up {}", id);
//        findUserAsync(id); // Trigger the asynchronous call
        User user = firstRepository.findUserAsync(id).join();
        logger.info("findUserAsynchronously3: Found user: {}", user);
        return user;
    }

    @Async
    public CompletableFuture<User> findUserAsync(Integer id) {
        logger.info("findUserAsync: Looking up {}", id);
        CompletableFuture<User> userCompletableFuture = completableFutureExecutor.supplyAsync(() -> {
            User user = firstClient.findById(id);
            logger.info("findUserAsync.userCompletableFuture: Found user: {}", user);
            return user;
        });
        logger.info("findUserAsync: Returning CompletableFuture for user with ID: {}", id);
        return userCompletableFuture;
    }

    @Async
    public CompletableFuture<User> findUserAsync4(Integer id) {
        logger.info("findUserAsync4: Looking up {}", id);
        CompletableFuture<User> userCompletableFuture = completableFutureExecutor.supplyAsync(() -> {
            User user = firstClient.findById(id);
            logger.info("findUserAsync4.userCompletableFuture: Found user: {}", user);
            return user;
        });
        logger.info("findUserAsync4: Returning CompletableFuture for user with ID: {}", id);
        return userCompletableFuture;
    }

}
