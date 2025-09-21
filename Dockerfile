FROM openjdk:21

WORKDIR /app

COPY ./target/turnosbackend-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "turnosbackend-0.0.1-SNAPSHOT.jar"]