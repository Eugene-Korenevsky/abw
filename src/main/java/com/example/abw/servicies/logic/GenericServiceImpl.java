package com.example.abw.servicies.logic;

import com.example.abw.entities.exception.ValidationError;
import com.example.abw.servicies.GenericService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.validator.MyValidator;
import com.example.abw.exception.validation.ValidationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public abstract class GenericServiceImpl<T> implements GenericService<T> {
    private Class<T> entityClass;
    private CrudRepository<T, Long> crudRepository;

    public GenericServiceImpl(CrudRepository<T, Long> crudRepository, Class entityClass) {
        this.crudRepository = crudRepository;
        this.entityClass = entityClass;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T findById(Long id) throws ResourceNotFoundException, IllegalArgumentException {
        if (id != null) {
            Optional<T> optionalT = crudRepository.findById(id);
            if (optionalT.isPresent()) return optionalT.get();
            else throw new ResourceNotFoundException("resource not found");
        } else throw new IllegalArgumentException("long must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) throws IllegalArgumentException {
        if (id != null) {
            crudRepository.deleteById(id);
        } else throw new IllegalArgumentException("long must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T create(T entity) throws ValidationException {
        if (entity != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(entity);
            if (violations.size() < 1) {
                return crudRepository.save(entity);
            } else {
                ValidationError validationError = new ValidationError();
                for (ConstraintViolation<T> violation : violations) {
                    validationError.addError(violation.getMessage());
                }
                throw new ValidationException("ValidationException", validationError);
            }
        } else throw new ValidationException("entity must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T update(T entity, Long id) throws ValidationException, ResourceNotFoundException {
        if (entity != null) {
            if (id != null) {
                if (crudRepository.existsById(id)) {
                    Validator validator = MyValidator.getValidator();
                    Set<ConstraintViolation<T>> violations = validator.validate(entity);
                    if (violations.size() < 1) {
                        return crudRepository.save(entity);
                    } else {
                        ValidationError validationError = new ValidationError();
                        for (ConstraintViolation<T> violation : violations) {
                            validationError.addError(violation.getMessage());
                        }
                        throw new ValidationException("entity validation error", validationError);
                    }
                } else throw new ResourceNotFoundException("resource not exist");
            } else throw new IllegalArgumentException("long must not be null");
        } else throw new IllegalArgumentException("entity must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<T>();
        Iterable<T> iterable = crudRepository.findAll();
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<T> saveAll(List<T> entityList) {
        List<T> result = new ArrayList<T>();
        Iterable<T> iterable = crudRepository.saveAll(entityList);
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
