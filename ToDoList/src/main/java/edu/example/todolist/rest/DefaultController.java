package edu.example.todolist.rest;

import edu.example.todolist.model.Task;
import edu.example.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Task> taskList = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskList.forEach(task -> tasks.add(task));
        model.addAttribute("tasks",tasks);
        return "index";
    }
}
