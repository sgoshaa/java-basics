package edu.example.todolist.service.exceptions;

public class TaskNotException extends NullPointerException {
    public TaskNotException(String s) {
        super(s);
    }
}
