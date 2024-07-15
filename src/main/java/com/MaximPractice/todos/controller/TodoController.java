package com.MaximPractice.todos.controller;


import com.MaximPractice.todos.model.DTO.NewToDoDTO;
import com.MaximPractice.todos.model.DTO.ToDoDTO;
import com.MaximPractice.todos.model.DTO.TodoFilter;
import com.MaximPractice.todos.model.DTO.TodoFilters;
import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    TodoService todoService;

    @GetMapping
    public ResponseEntity<List<ToDoDTO>> GetAllTodos(TodoFilter filter){
        var allTodos = todoService.getAllModels(filter);
        return ResponseEntity.ok(allTodos);
    }

    @GetMapping("today")
    public ResponseEntity<List<ToDoDTO>> GetAllTodosToday(boolean isCompleted){
        var filter = TodoFilters.getTodayFilter(isCompleted);
        return GetAllTodos(filter);
    }

    @GetMapping("week")
    public ResponseEntity<List<ToDoDTO>> GetAllTodosWeek(boolean isCompleted){
        var filter = TodoFilters.getWeekFilter(isCompleted);
        return GetAllTodos(filter);
    }

    @GetMapping("month")
    public ResponseEntity<List<ToDoDTO>> GetAllTodosMonth(boolean isCompleted){
        var filter = TodoFilters.getMonthFilter(isCompleted);
        return GetAllTodos(filter);
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoDTO> GetTodoById(@PathVariable Long id) {
        var todo = todoService.GetModel(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("create")
    public ResponseEntity<ToDoDTO> CreateTodo(@RequestBody NewToDoDTO todo){
        var newTodo = todoService.CreateModel(todo);
        return ResponseEntity.ok(newTodo);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ToDoDTO> UpdateTodo(@PathVariable Long id, @RequestBody NewToDoDTO todo){
        var updatedTodo = todoService.UpdateModel(id, todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> DeleteTodo(@PathVariable Long id){
        todoService.RemoveModel(id);
        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("changeCompleteCheckbox/{id}")
    public ResponseEntity<ToDoDTO> ChangeCompleteCheckbox(@PathVariable Long id){
        var newTodo = todoService.changeCompletedCheckbox(id);
        return ResponseEntity.ok(newTodo);
    }
}
