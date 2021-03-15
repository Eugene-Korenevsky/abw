package com.example.abw.servicies.logic;

import com.example.abw.servicies.GenericService;
import com.example.abw.validator.MyValidator;
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
    public T findById(Long id) {
        if (id != null) {
            Optional<T> optionalT = crudRepository.findById(id);
            if (optionalT.isPresent()) return optionalT.get();
            else return null; //throw new ResourceNotFoundException("");
        } else return null; //else throw new ValidationException("long must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        if (id != null) {
            crudRepository.deleteById(id);
        }  //else throw new ValidationException("long must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T create(T entity) {
        if (entity != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(entity);
            if (violations.size() < 1) {
                return crudRepository.save(entity);
            } else {
                //  ValidationError validationError = new ValidationError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<T> violation : violations) {
                    errors.add(violation.getMessage());
                }
                // validationError.setErrors(errors);
                return null;  // throw new ValidationException("entity validation error", validationError);
            }
        }
        return null; //else throw new ValidationException("entity must not be null");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T update(T entity, Long id) {
        if (entity != null) {
            if (id != null) {
                if (crudRepository.existsById(id)) {
                    Validator validator = MyValidator.getValidator();
                    Set<ConstraintViolation<T>> violations = validator.validate(entity);
                    if (violations.size() < 1) {
                        return crudRepository.save(entity);
                    } else {
                        // ValidationError validationError = new ValidationError();
                        ArrayList<String> errors = new ArrayList<>();
                        for (ConstraintViolation<T> violation : violations) {
                            errors.add(violation.getMessage());
                        }
                        //  validationError.setErrors(errors);
                        // throw new ValidationException("entity validation error", validationError);
                    }
                } //else throw new ResourceNotFoundException("resource not exist");
            } //else throw new ValidationException("long must not be null");
        } //else throw new ValidationException("entity must not be null");
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<T>();
        Iterable<T> iterable = crudRepository.findAll();
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }

    public List<T> saveAll(List<T> entityList) {
        List<T> result = new ArrayList<T>();
        Iterable<T> iterable = crudRepository.saveAll(entityList);
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
