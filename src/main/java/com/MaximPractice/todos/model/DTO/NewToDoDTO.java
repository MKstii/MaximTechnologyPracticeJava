package com.MaximPractice.todos.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewToDoDTO {
    private String text;
    private LocalDate deadline;
}
