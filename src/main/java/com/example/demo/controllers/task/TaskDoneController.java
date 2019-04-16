package com.example.demo.controllers.task;

import com.example.demo.repositories.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskDoneController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/done")
    void MarkTaskAsDone(@RequestParam("taskId") String taskId) {
        int taskIdInt = Integer.parseInt(taskId);
        try {
            taskRepository.markTaskAsDone((long) taskIdInt);
        } catch (Exception ignored) {
        }
    }
}
