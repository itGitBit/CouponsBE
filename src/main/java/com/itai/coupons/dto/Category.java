package com.itai.coupons.dto;

import com.itai.coupons.entities.CategoryEntity;

public class Category {

    private int id;

    private String categoryName;

    public Category() {
    }

    public Category(CategoryEntity category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
