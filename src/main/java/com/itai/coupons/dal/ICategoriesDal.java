package com.itai.coupons.dal;

import com.itai.coupons.dto.Category;
import com.itai.coupons.dto.Company;
import com.itai.coupons.entities.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICategoriesDal extends CrudRepository<CategoryEntity, Integer> {

    @Query("SELECT new com.itai.coupons.dto.Category( c.id, c.categoryName) FROM CategoryEntity c")
    List<Category> getAllCategories();

    @Query("SELECT new com.itai.coupons.dto.Category( c.id, c.categoryName) FROM CategoryEntity c where c.categoryName=:categoryName")
    Category getCategoryIdByCategoryName(String categoryName);
}
