package dev.annopud.second_spring_clients;

import brave.propagation.CurrentTraceContext;
import dev.annopud.second_spring_clients.config.CompletableFutureExecutor;
import dev.annopud.second_spring_clients.user.UserHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAsync
public class SpringClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringClientsApplication.class, args);
    }

    @Bean
    UserHttpClient userHttpClient() {
        RestClient restClient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
            .build();
        return factory.createClient(UserHttpClient.class);
    }

    @Bean
    public Executor taskExecutor(CurrentTraceContext currentTraceContext) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.setTaskDecorator(currentTraceContext::wrap);

        executor.initialize();
        return executor;
    }

//	@Bean
//	public Executor taskExecutor(CurrentTraceContext currentTraceContext) {
//		ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
//
//		// Wrap tasks to preserve trace context
//		return command -> virtualExecutor.execute(currentTraceContext.wrap(command));

    /// /		return command -> virtualExecutor.execute(command);
//	}
    @Bean
    public CompletableFutureExecutor tracingAsync(Executor tracingExecutor) {
        return new CompletableFutureExecutor(tracingExecutor);
    }
}
