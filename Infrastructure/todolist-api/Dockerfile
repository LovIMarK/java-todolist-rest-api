FROM openjdk:21
WORKDIR /app
COPY target/todolist-api-1.0-SNAPSHOT.jar todolist-api.jar
EXPOSE 7000
CMD ["java", "-jar", "todolist-api.jar"]
