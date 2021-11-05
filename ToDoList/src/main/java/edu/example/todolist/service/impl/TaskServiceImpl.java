package edu.example.todolist.service.impl;

import edu.example.todolist.dto.TaskRequestDTO;
import edu.example.todolist.model.Task;
import edu.example.todolist.repository.TaskRepository;
import edu.example.todolist.service.TaskService;
import edu.example.todolist.service.exceptions.TaskNotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public int addTask(Task task) {
        if (task.getName()==""){
            return -1;
        }
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @Override
    public List<Task> getTasks() {
        Iterable<Task>taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(task -> tasks.add(task));
        return tasks;
    }

    @Override
    public Task getTask(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.get();
    }

    @Override
    public void updateTask(int id, Task task) {
        Task tempTask =  taskRepository.findById(id).get();
        tempTask = task;
        taskRepository.save(tempTask);

    }

    @Override
    public void deleteTask(int id) {
        Optional<Task> optinalTask = taskRepository.findById(id);
        if (!optinalTask.isPresent()){
            new TaskNotException("Задача не найдена.");
        }
        taskRepository.delete(optinalTask.get());
    }

    @Override
    public void deleteAllTask() {
        taskRepository.deleteAll();
    }
}
