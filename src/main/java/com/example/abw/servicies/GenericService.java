package com.example.abw.servicies;

import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;

import java.util.List;

public interface GenericService<T> {
    public T findById(Long id) throws ResourceNotFoundException,IllegalArgumentException;

    public void deleteById(Long id) throws IllegalArgumentException;

    public T create(T entity) throws ValidationException;

    public T update(T entity, Long id)throws ValidationException,ResourceNotFoundException;

    public List<T> findAll();

    public List<T> saveAll(List<T> entityList);
}
