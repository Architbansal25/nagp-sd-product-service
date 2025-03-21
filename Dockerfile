# Stage 1: Build with Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /build

# Copy pom.xml and download dependencies first (to leverage Docker cache)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the JAR
RUN mvn clean install -DskipTests

# Stage 2: Run the application with slim JDK
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /build/target/product-service-*.jar product-service.jar

# Expose the application port
EXPOSE 9091

# Run the application
ENTRYPOINT ["java", "-jar", "product-service.jar"]
