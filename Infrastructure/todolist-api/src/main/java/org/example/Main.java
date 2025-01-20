package org.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create();
        TaskController taskController = new TaskController();

        // Add CORS headers
        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "https://static-web.localhost"); // Allow requests only from static site
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type");
            ctx.header("Access-Control-Allow-Credentials", "true"); 
        });

        // Handle preflight requests
        app.options("/*", ctx -> {
            ctx.header("Access-Control-Allow-Origin", "https://static-web.localhost");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type");
            ctx.header("Access-Control-Allow-Credentials", "true");
            ctx.status(204); // No Content
        });


        // Routes
        app.get("/tasks", taskController::getAllTasks);
        app.get("/tasks/{id}", taskController::getTaskById);
        app.post("/tasks", taskController::createTask);
        app.put("/tasks/{id}", taskController::updateTask);
        app.delete("/tasks/{id}", taskController::deleteTask);

        app.start(7000);
    }
}
