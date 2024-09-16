FROM openjdk:17-jdk-alpine
LABEL author="brenomota"
WORKDIR /app
COPY target/historico-service-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
