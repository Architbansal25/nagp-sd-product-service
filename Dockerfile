FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file from target folder
COPY target/product-service-*.jar product-service.jar

# Expose the application port
EXPOSE 9091

# Run the application
ENTRYPOINT ["java", "-jar", "product-service.jar"]