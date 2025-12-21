# 1. Lightweight Java runtime
FROM eclipse-temurin:24-jre

# 2. Set working directory inside container
WORKDIR /app

# 3. Copy built jar from target folder
COPY target/myfitness-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose Spring Boot port
EXPOSE 8080

# 5. Run the application
ENTRYPOINT ["java","-jar","app.jar"]
