FROM openjdk:17-alpine
WORKDIR /app
COPY target/svitsmachnogo-0.0.1-SNAPSHOT.jar svitsmachnogo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/svitsmachnogo.jar"]
