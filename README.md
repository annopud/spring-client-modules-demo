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
