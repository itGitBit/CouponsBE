package com.itai.coupons.controllers;


import com.itai.coupons.dto.Category;
import com.itai.coupons.entities.CategoryEntity;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.logic.CategoriesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private CategoriesLogic categoriesLogic;

    @Autowired
    public CategoriesController(CategoriesLogic categoriesLogic) {
        this.categoriesLogic = categoriesLogic;
    }

    @PostMapping
    public void addCategory( @RequestBody Category category) throws ApplicationException {
        categoriesLogic.addCategory(category);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) throws ApplicationException {
        categoriesLogic.updateCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() throws ApplicationException {
        return categoriesLogic.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") int categoryId) throws ApplicationException {
        return categoriesLogic.getCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId) throws ApplicationException {
        categoriesLogic.deleteCategory(categoryId);
    }


}
