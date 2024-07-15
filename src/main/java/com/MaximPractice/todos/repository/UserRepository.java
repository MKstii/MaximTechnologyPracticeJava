package com.MaximPractice.todos.repository;

import com.MaximPractice.todos.model.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTodo, Long> {
    public UserTodo findByUsername(String username);
}
