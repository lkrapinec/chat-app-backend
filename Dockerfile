FROM openjdk:17
COPY /application/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]