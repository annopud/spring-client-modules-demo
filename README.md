# Demo Topics
## spring cloud loadbalancer
This repository demonstrates how to use Spring Cloud LoadBalancer with a simple microservice architecture.

## micrometer distributed tracing
This repository shows how to implement distributed tracing using Micrometer in a Spring Boot application.
and propagate trace context across `asynchronous` calls.

## migration from `spring cloud openfeign` to `http interface`. 
This repository provides a guide on migrating from `spring cloud openfeign` to using `http interface` rest client for making HTTP calls in a Spring Boot application.

---
To support `org.owasp:dependency-check-maven`

configure the NVD API key in your Maven `settings.xml` file and the plugin in your `pom.xml`.

`settings.xml`
```xml
<settings>
    <servers>
        <server>
            <id>nvd-api</id>
            <username></username> <!-- not used by NVD; can be left blank -->
            <password>{NVD_API_KEY}</password>
        </server>
    </servers>
</settings>
```

`pom.xml`
```xml
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>12.1.1</version>
    <configuration>
        <nvdApiServerId>nvd-api</nvdApiServerId>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
