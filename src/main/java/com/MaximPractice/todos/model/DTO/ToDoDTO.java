package com.MaximPractice.todos.model.DTO;

import com.MaximPractice.todos.model.ToDo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ToDoDTO {
    private Long id;
    private String text;
    private boolean isCompleted;
    private LocalDate deadline;

    public ToDoDTO(ToDo todo) {
        id = todo.getId();
        text = todo.getText();
        isCompleted = todo.isCompleted();
        deadline = todo.getDeadline();
    }
}


