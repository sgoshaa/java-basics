package edu.example.todolist.rest;

import edu.example.todolist.dto.TaskRequestDTO;
import edu.example.todolist.model.Task;
import edu.example.todolist.service.ConverterDtoToModel;
import edu.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/")
    public synchronized List<Task> list(){
        return taskService.getTasks();
    }

    @PostMapping("/tasks/")
    public synchronized int add(TaskRequestDTO taskRequestDTO, Model model){
        Task task = ConverterDtoToModel.convertDtoToModel(taskRequestDTO);
        model.addAttribute("task",task);
        return taskService.addTask(task);
    }

    @GetMapping("/tasks/{id}")
    public synchronized ResponseEntity get(@PathVariable int id){
        Task task = taskService.getTask(id);
        if (task==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task,HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public synchronized ResponseEntity update(@PathVariable int id,TaskRequestDTO taskRequestDTO){
        Task task = ConverterDtoToModel.convertDtoToModel(taskRequestDTO);
        taskService.updateTask(id,task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public synchronized ResponseEntity delete(@PathVariable int id){
       taskService.deleteTask(id);
       return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/tasks/clear")
    public synchronized ResponseEntity deleteAll(){
        taskService.deleteAllTask();
        taskService.getTasks();
        return taskService.getTasks().size() == 0
                 ? new ResponseEntity(HttpStatus.OK)
                 : new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
}
