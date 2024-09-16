FROM openjdk:17-jdk-alpine
LABEL author="brenomota"
WORKDIR /app
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
