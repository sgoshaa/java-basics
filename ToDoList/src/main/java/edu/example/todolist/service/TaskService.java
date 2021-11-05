package edu.example.todolist.service;

import edu.example.todolist.model.Task;

import java.util.List;

public interface TaskService {

    int addTask(Task task);

    List<Task> getTasks();

    Task getTask(int id);

    void updateTask(int id, Task task);

    void deleteTask(int id);

    void deleteAllTask();

}
