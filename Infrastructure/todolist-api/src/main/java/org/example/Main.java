package org.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create();
        TaskController taskController = new TaskController();

        // Routes avec la nouvelle syntaxe
        app.get("/tasks", taskController::getAllTasks);
        app.get("/tasks/{id}", taskController::getTaskById);
        app.post("/tasks", taskController::createTask);
        app.put("/tasks/{id}", taskController::updateTask);
        app.delete("/tasks/{id}", taskController::deleteTask);

        app.start(7000);
    }
}