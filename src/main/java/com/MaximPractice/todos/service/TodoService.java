package com.MaximPractice.todos.service;

import com.MaximPractice.todos.model.DTO.NewToDoDTO;
import com.MaximPractice.todos.model.DTO.ToDoDTO;
import com.MaximPractice.todos.model.DTO.TodoFilter;
import com.MaximPractice.todos.model.Exceptions.NonAuthorizedException;
import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.model.UserTodo;
import com.MaximPractice.todos.repository.TodoRepository;
import com.MaximPractice.todos.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService{
    TodoRepository todoRepository;
    UserRepository userRepository;

    public ToDoDTO CreateModel(NewToDoDTO toDo) {
        var userDB = GetAuthUser();

        var toDoDB  = new ToDo();
        toDoDB.setUser(userDB);
        toDoDB.setText(toDo.getText());
        toDoDB.setDeadline(toDo.getDeadline());
        toDoDB.setCompleted(false);
        todoRepository.save(toDoDB);

        return new ToDoDTO(toDoDB);
    }

    public ToDoDTO GetModel(Long id) throws NonAuthorizedException {
        var toDoDB = todoRepository.findById(id).get();
        if(!UserIsOwner(toDoDB)){
            throw new NonAuthorizedException("Недостаточно прав");
        }
        return new ToDoDTO(toDoDB);
    }

    public ToDoDTO UpdateModel(Long id, NewToDoDTO toDo) throws NonAuthorizedException {
        var toDoDB = todoRepository.findById(id).get();
        if(!UserIsOwner(toDoDB)) {
            throw new NonAuthorizedException("Недостаточно прав");
        }

        toDoDB.setText(toDo.getText());
        toDoDB.setDeadline(toDo.getDeadline());
        todoRepository.save(toDoDB);
        return new ToDoDTO(toDoDB);
    }

    public void RemoveModel(Long id) throws NonAuthorizedException {
        var toDoDB = todoRepository.findById(id).get();
        if(!UserIsOwner(toDoDB)) {
            throw new NonAuthorizedException("Недостаточно прав");
        }
        todoRepository.deleteById(id);
    }

    public List<ToDoDTO> getAllModels(TodoFilter filter)
    {
        var userDB = GetAuthUser();
        var allTodos = todoRepository.findAllByUserAndFilter(userDB, filter);
        return allTodos.stream().map(ToDoDTO::new).toList();
    }

    public ToDoDTO changeCompletedCheckbox(Long id) throws NonAuthorizedException {
        var toDoDB = todoRepository.findById(id).get();
        if(!UserIsOwner(toDoDB)) {
            throw new NonAuthorizedException("Недостаточно прав");
        }
        toDoDB.setCompleted(!toDoDB.isCompleted());
        todoRepository.save(toDoDB);
        return new ToDoDTO(toDoDB);
    }

    private boolean UserIsOwner(ToDo todoDB){
        var userDB = GetAuthUser();
        return todoDB.getUser().getId().equals(userDB.getId());
    }

    private UserTodo GetAuthUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var name = auth.getName();
        return userRepository.findByUsername(name);
    }
}
