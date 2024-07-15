package com.MaximPractice.todos.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoFilter{
    LocalDate deadlineStart;
    LocalDate deadlineEnd;
    boolean isCompleted;
}
