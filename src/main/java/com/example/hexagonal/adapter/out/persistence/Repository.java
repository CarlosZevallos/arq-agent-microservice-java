package com.example.hexagonal.adapter.out.persistence;

public interface Repository {
    void save(Entity entity);
    Entity findById(String id);
}