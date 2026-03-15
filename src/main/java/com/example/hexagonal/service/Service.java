package com.example.hexagonal.service;

import com.example.hexagonal.adapter.out.persistence.Repository;
import com.example.hexagonal.domain.Entity;

public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void createEntity(Entity entity) {
        repository.save(entity);
    }
}