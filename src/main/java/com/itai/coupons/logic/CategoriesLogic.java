package com.itai.coupons.logic;


import com.itai.coupons.dal.ICategoriesDal;
import com.itai.coupons.dto.Category;
import com.itai.coupons.entities.CategoryEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriesLogic {

    private ICategoriesDal categoriesDal;

    @Autowired
    public CategoriesLogic(ICategoriesDal categoriesDal) {
        this.categoriesDal = categoriesDal;
    }

    public void addCategory(Category category) throws ApplicationException {
        validateCategory(category);
        CategoryEntity categoryEntity = new CategoryEntity(category);
        StatisticsUtils.sendStatistics("Category added, category: " + category.getId());
        categoriesDal.save(categoryEntity);
    }

    public Category getCategory(int categoryId) throws ApplicationException {
        CategoryEntity categoryEntity = categoriesDal.findById(categoryId).get();
        Category category = new Category(categoryEntity);
        StatisticsUtils.sendStatistics("Category read, category: " + categoryId);
        return category;
    }

    public List<Category> getAllCategories() throws ApplicationException {
        return categoriesDal.getAllCategories();
    }

    public void updateCategory(Category category) throws ApplicationException {
        validateCategory(category);
        CategoryEntity categoryEntity = new CategoryEntity(category);
        categoriesDal.save(categoryEntity);
        StatisticsUtils.sendStatistics("Category updated, category: " + category.getId());
    }

    public void deleteCategory(int categoryId) throws ApplicationException {
        categoriesDal.deleteById(categoryId);
        StatisticsUtils.sendStatistics("Category removed, category: " + categoryId);
    }


    private void validateCategory(Category category) throws ApplicationException {
        validateCategoryName(category.getCategoryName());
    }

    private void validateCategoryName(String categoryName) throws ApplicationException {
        if (categoryName.length() > 45) {
            throw new ApplicationException(ErrorType.NAME_TOO_LONG);
        }
        if (categoryName.length() < 2) {
            throw new ApplicationException(ErrorType.NAME_TOO_SHORT);
        }
    }


    boolean isCategoryExist(int categoryId) throws ApplicationException {
        return categoriesDal.existsById(categoryId);
    }

    public int getCategoryIdByCategoryName(String categoryName) {
        return categoriesDal.getCategoryIdByCategoryName(categoryName).getId();
    }
}
