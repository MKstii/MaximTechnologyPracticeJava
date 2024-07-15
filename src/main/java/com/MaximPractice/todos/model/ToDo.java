package com.MaximPractice.todos.model;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name="userid", nullable=false)
    private UserTodo user;
}
