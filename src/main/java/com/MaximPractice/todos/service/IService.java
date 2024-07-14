package com.MaximPractice.todos.service;

import java.util.List;

public interface IService<Model> {
    public Model CreateModel(Model model);
    public Model GetModel(Long id);
    public Model UpdateModel(Model model);
    public void RemoveModel(Long id);
    public List<Model> getAllModels();
}
