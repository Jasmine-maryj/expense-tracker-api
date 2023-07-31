package com.dev.expensetrackerapi.repository;

import com.dev.expensetrackerapi.ehandler.EtBadRequestException;
import com.dev.expensetrackerapi.ehandler.EtResourceNotFoundException;
import com.dev.expensetrackerapi.entity.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws EtBadRequestException;

    void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId);

}
