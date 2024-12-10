# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080
EXPOSE 9010

# Run the jar file
ENTRYPOINT ["java", \
    "-Dcom.sun.management.jmxremote=true", \
    "-Dcom.sun.management.jmxremote.port=9010", \
    "-Dcom.sun.management.jmxremote.rmi.port=9010", \
    "-Dcom.sun.management.jmxremote.authenticate=false", \
    "-Dcom.sun.management.jmxremote.ssl=false", \
    "-Djava.rmi.server.hostname=0.0.0.0", \
    "-jar", "app.jar", \
    "--spring.profiles.active=prod"]