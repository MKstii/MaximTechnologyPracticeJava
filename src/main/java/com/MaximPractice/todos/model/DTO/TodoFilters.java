package com.MaximPractice.todos.model.DTO;

import java.time.LocalDate;

public class TodoFilters{
    public static TodoFilter getTodayFilter(boolean isCompleted) {
        TodoFilter todayFilter = new TodoFilter();
        todayFilter.isCompleted = isCompleted;
        todayFilter.deadlineStart = LocalDate.now();
        todayFilter.deadlineEnd = LocalDate.now();
        return todayFilter;
    }

    public static TodoFilter getWeekFilter(boolean isCompleted) {
        TodoFilter todayFilter = new TodoFilter();
        todayFilter.isCompleted = isCompleted;
        todayFilter.deadlineStart = LocalDate.now();
        todayFilter.deadlineEnd = LocalDate.now().plusWeeks(1);
        return todayFilter;
    }

    public static TodoFilter getMonthFilter(boolean isCompleted) {
        TodoFilter todayFilter = new TodoFilter();
        todayFilter.isCompleted = isCompleted;
        todayFilter.deadlineStart = LocalDate.now();
        todayFilter.deadlineEnd = LocalDate.now().plusMonths(1);
        return todayFilter;
    }
}
