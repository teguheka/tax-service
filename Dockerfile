# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="ekaputra1608@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Add the application's jar to the container
ADD target/tax-service.jar tax-service.jar

# Make port 8084 available to the world outside this container
EXPOSE 8084

# Run the jar file
ENTRYPOINT ["java","-jar","tax-service.jar"]