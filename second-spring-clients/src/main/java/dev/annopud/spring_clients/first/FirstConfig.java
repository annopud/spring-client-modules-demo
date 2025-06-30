package dev.annopud.spring_clients.first;

import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
@LoadBalancerClients({
    @LoadBalancerClient(value = "first-spring-clients-service", configuration = SayHelloConfiguration.class)
})
public class FirstConfig {
//    @Bean
//    @LoadBalanced
//    public RestClient.Builder loadBalancedRestClientBuilder() {
//        return RestClient.builder();
//    }

    /**
     * @see <a href="https://docs.spring.io/spring-cloud-commons/reference/spring-cloud-commons/common-abstractions.html#rest-client-loadbalancer-client">
     *     Spring RestClient as a LoadBalancer Client</a>
     *     </a>
     */
    @LoadBalanced
    @Bean
    RestClient.Builder restClientBuilder(RestClientBuilderConfigurer configurer) {
        return configurer.configure(RestClient.builder());
    }

    @Bean
    FirstClient firstClient(RestClient.Builder loadBalancedRestClientBuilder) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
            .builderFor(
                RestClientAdapter.create(
                    loadBalancedRestClientBuilder.baseUrl(
                        "http://first-spring-clients-service" // This is the service name in Kubernetes
//                        "http://localhost:8080" // For local testing, replace with your service URL
                    ).build()
                )
            )
            .build();
        return factory.createClient(FirstClient.class);
    }

}
