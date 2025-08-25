# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Expose application port
EXPOSE 8081

# Copy the WAR into the container
ADD target/demo.war demo.war

# Run the WAR
ENTRYPOINT ["java","-jar","/demo.war"]