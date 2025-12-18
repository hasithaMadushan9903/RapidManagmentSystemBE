# Step 1: Build the Spring Boot JAR
FROM maven:3.9.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run the JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
VOLUME ["/app/images"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
