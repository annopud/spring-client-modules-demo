FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

ENV TZ=Asia/Bangkok

WORKDIR /app

COPY settings.xml /root/.m2/settings.xml

COPY . .

RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21.0.7_6-jre-noble
ENV TZ=Asia/Bangkok

USER root

RUN useradd -ms /bin/bash appuser && mkdir -p /app && chown appuser:appuser /app

COPY --from=build /app/target/spring-clients-develop.jar /app/app.jar

RUN chown appuser:appuser /app/app.jar

USER appuser

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/app.jar"]

#docker build -t spring-clients-develop .
#docker run --rm -it -p 8081:8081 --name spring-clients-develop-ctn -d spring-clients-develop