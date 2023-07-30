package com.dev.expensetrackerapi.service;

import com.dev.expensetrackerapi.ehandler.EtBadRequestException;
import com.dev.expensetrackerapi.ehandler.EtResourceNotFoundException;
import com.dev.expensetrackerapi.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryServiceImpl> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

    void updateCategory(Integer userId, Integer CategoryId, Category category) throws EtBadRequestException;

    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;
}
