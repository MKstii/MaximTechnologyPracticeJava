package com.MaximPractice.todos.repository;

import com.MaximPractice.todos.model.DTO.TodoFilter;
import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.model.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<ToDo, Long>, TodoFilterRepository {
    void deleteById(Long id);
    List<ToDo> findAllByUser(UserTodo user);
}
