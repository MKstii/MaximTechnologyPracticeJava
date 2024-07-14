package com.MaximPractice.todos.controller;


import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    TodoService todoService;
    //ResponseEntity
    @GetMapping
    public List<ToDo> GetAllTodos(){
        return todoService.getAllModels();
    }

    @GetMapping("{id}")
    public ToDo GetTodoById(@PathVariable Long id){
        return todoService.GetModel(id);
    }

    @PostMapping("create")
    public ToDo CreateTodo(ToDo todo){
        return todoService.CreateModel(todo);
    }

    @PutMapping("update")
    public ToDo UpdateTodo(ToDo todo){
        return todoService.UpdateModel(todo);
    }

    @DeleteMapping("delete/{id}")
    public void DeleteTodo(@PathVariable Long id){
        todoService.RemoveModel(id);
    }
}
