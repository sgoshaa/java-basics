package edu.example.todolist.service;

import edu.example.todolist.dto.TaskRequestDTO;
import edu.example.todolist.model.Task;

public class ConverterDtoToModel {
    public static Task convertDtoToModel(TaskRequestDTO taskRequestDTO){
        return Task.builder()
                .id(taskRequestDTO.getId())
                .name(taskRequestDTO.getName())
                .date(taskRequestDTO.getDate())
                .build();
    }
}
