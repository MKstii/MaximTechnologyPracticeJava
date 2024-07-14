package com.MaximPractice.todos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="Todos")
public class ToDo {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private boolean isCompleted;
    private LocalDate deadline;
}
