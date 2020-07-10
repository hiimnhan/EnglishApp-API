package com.group1.EnglishApp.service;

import com.group1.EnglishApp.repository.GenericRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Hai Dang
 */
public abstract class BaseService<T, ID extends Serializable> {
    protected final static Logger logger = LoggerFactory.getLogger(BaseService.class);

    private GenericRepository<T, ID> repository;

    public BaseService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    public boolean exists(ID id) {
        return repository.existsById(id);
    }

    public T save(T object) {
        return repository.save(object);
    }

    public List<T> save(List<T> objects) {
        return repository.saveAll(objects);
    }

    public void remove(T object) {
        repository.delete(object);
    }

    public void remove(ID id) {
        repository.deleteById(id);
    }

    public void flush() {
        repository.flush();
    }

    public T saveAndFlush(T entity) {
        return repository.saveAndFlush(entity);
    }
}
