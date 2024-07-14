package com.MaximPractice.todos.repository;

import com.MaximPractice.todos.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<ToDo, Long> {
    void deleteById(Long id);
}
