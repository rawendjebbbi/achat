# Use a multi-stage build to keep the final image smaller
# Stage 1: Build the application
FROM maven:3.8.1-openjdk-11 as build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .

# Download all dependencies before copying the rest of the source code
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/achat-1.0.jar app.jar

# Expose the application port (adjust if your application uses a different port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
# Use a multi-stage build to keep the final image smaller
# Stage 1: Build the application
FROM maven:3.8.1-openjdk-11 as build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .

# Download all dependencies before copying the rest of the source code
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/achat-1.0.jar app.jar

# Expose the application port (adjust if your application uses a different port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
