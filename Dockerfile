FROM openjdk:11
WORKDIR /app
COPY target .
CMD ["java", "-jar", "voluntarious-app-0.0.1-SNAPSHOT.jar"]