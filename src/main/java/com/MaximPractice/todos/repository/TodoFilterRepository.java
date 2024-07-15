package com.MaximPractice.todos.repository;

import com.MaximPractice.todos.model.DTO.TodoFilter;
import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.model.UserTodo;

import java.util.List;

public interface TodoFilterRepository {
    List<ToDo> findAllByUserAndFilter(UserTodo user, TodoFilter filter);
}
