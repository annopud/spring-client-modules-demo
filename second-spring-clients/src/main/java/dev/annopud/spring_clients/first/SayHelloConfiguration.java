package dev.annopud.spring_clients.first;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class SayHelloConfiguration {

    @Bean
    @Profile("local")
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSuppler("first-spring-clients-service");
    }

}
