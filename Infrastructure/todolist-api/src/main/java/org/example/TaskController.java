package org.example;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class TaskController {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int lastId = 0;

    public TaskController() {
        // Example tasks
        tasks.put(++lastId, new Task(lastId, "Initial Task", "This is a preloaded task"));
        tasks.put(++lastId, new Task(lastId, "Buy groceries", "Milk, eggs, bread, and coffee"));
        tasks.put(++lastId, new Task(lastId, "Finish report", "Complete the monthly performance report"));
        tasks.put(++lastId, new Task(lastId, "Call client", "Schedule a follow-up meeting for Project X"));
        tasks.put(++lastId, new Task(lastId, "Workout", "Go to the gym for a strength training session"));
    }

    public void getAllTasks(Context ctx) {
        ctx.json(tasks.values());
    }

    public void getTaskById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (tasks.containsKey(id)) {
            ctx.json(tasks.get(id));
        } else {
            ctx.status(404).result("Task not found");
        }
    }

    public void createTask(Context ctx) {
        Task task = ctx.bodyAsClass(Task.class);
        task.setId(++lastId); // new task id
        tasks.put(lastId, task);
        ctx.status(201).json(task);
    }

    public void updateTask(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (tasks.containsKey(id)) {
            Task updatedTask = ctx.bodyAsClass(Task.class);
            updatedTask.setId(id);
            tasks.put(id, updatedTask);
            ctx.status(200).json(updatedTask);
        } else {
            ctx.status(404).result("Task not found");
        }
    }

    public void deleteTask(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (tasks.remove(id) != null) {
            ctx.status(204); // No Content
        } else {
            ctx.status(404).result("Task not found");
        }
    }
}
