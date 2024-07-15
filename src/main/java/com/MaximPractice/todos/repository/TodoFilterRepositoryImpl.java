package com.MaximPractice.todos.repository;

import com.MaximPractice.todos.model.DTO.TodoFilter;
import com.MaximPractice.todos.model.ToDo;
import com.MaximPractice.todos.model.UserTodo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TodoFilterRepositoryImpl implements TodoFilterRepository{
    private final EntityManager em;
    @Override
    public List<ToDo> findAllByUserAndFilter(UserTodo user, TodoFilter filter) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(ToDo.class);
        var todo = query.from(ToDo.class);

        List<Predicate> predicates = new ArrayList<>();
        if(filter.getDeadlineStart() != null) {
            predicates.add(cb.greaterThanOrEqualTo(todo.get("deadline"), filter.getDeadlineStart()));
        }
        if(filter.getDeadlineEnd() != null){
            predicates.add(cb.lessThanOrEqualTo(todo.get("deadline"), filter.getDeadlineEnd()));
        }
        if(filter.isCompleted()){
            predicates.add(cb.equal(todo.get("isCompleted"), filter.isCompleted()));
        }
        query.where(predicates.toArray(Predicate[]::new));

        return em.createQuery(query).getResultList();
    }
}
