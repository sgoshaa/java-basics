package edu.example.todolist.repository;

import edu.example.todolist.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private  static HashMap<Integer, Task> toDolist = new HashMap<Integer, Task>();

    public static int addTask(Task task){
        int id = currentId++;
        task.setId(id);
        toDolist.put(id,task);
        return id;
    }

    public static List<Task> getAllTasks(){
        ArrayList tasks = new ArrayList();
        tasks.addAll(toDolist.values());
        return tasks;
    }

    public static void clearToDoList(){
        toDolist.clear();
    }

    public static Task getTask(int taskID){

        if (toDolist.containsKey(taskID)){
            return toDolist.get(taskID);
        }
        return null;
    }

    public static boolean deleteTask(int taskID){
        return toDolist.remove(taskID) != null;
    }

    public static boolean updateTask(int taskId,Task task){
        if (toDolist.containsKey(taskId)){
            task.setId(taskId);
            toDolist.put(taskId,task);
            return true;
        }
        return false;
    }

    public static void clearList(){
        toDolist.clear();
    }
}
