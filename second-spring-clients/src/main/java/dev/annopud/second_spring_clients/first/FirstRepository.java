package dev.annopud.second_spring_clients.first;

import dev.annopud.second_spring_clients.config.CompletableFutureExecutor;
import dev.annopud.second_spring_clients.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class FirstRepository {
    private static final Logger logger = LoggerFactory.getLogger(FirstRepository.class);
    private final FirstClient firstClient;
    private final CompletableFutureExecutor completableFutureExecutor;

    public FirstRepository(FirstClient firstClient, CompletableFutureExecutor completableFutureExecutor) {
        this.firstClient = firstClient;
        this.completableFutureExecutor = completableFutureExecutor;
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

}
