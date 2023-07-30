package com.dev.expensetrackerapi.repository;

import com.dev.expensetrackerapi.ehandler.EtBadRequestException;
import com.dev.expensetrackerapi.ehandler.EtResourceNotFoundException;
import com.dev.expensetrackerapi.entity.Category;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository{

    @Override
    public List<Category> findAll(Integer userId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Integer create(Integer userId, String title, String description) throws EtBadRequestException {
        return null;
    }

    @Override
    public void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {

    }

    @Override
    public void removeById(Integer userId, Integer categoryId) {

    }
}
