package com.MaximPractice.todos.service;

import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService implements IService<ToDo>{
    TodoRepository todoRepository;

    @Override
    public ToDo CreateModel(ToDo toDo) {
        return todoRepository.save(toDo);
    }

    @Override
    public ToDo GetModel(Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public ToDo UpdateModel(ToDo toDo) {
        return todoRepository.save(toDo);
    }

    @Override
    public void RemoveModel(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAllModels() {
        return todoRepository.findAll();
    }
}
