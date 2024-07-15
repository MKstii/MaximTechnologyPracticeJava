package com.MaximPractice.todos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class UserTodo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
}
