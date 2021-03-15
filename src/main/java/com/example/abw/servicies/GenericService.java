package com.example.abw.servicies;

import java.util.List;

public interface GenericService<T> {
    public T findById(Long id);

    public void deleteById(Long id);

    public T create(T entity);

    public T update(T entity, Long id);

    public List<T> findAll();

    public List<T> saveAll(List<T> entityList);
}
