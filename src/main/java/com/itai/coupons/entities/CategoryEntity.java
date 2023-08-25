package com.itai.coupons.entities;


import com.itai.coupons.dto.Category;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<CouponEntity> couponsList;

    public CategoryEntity() {
    }

    public CategoryEntity(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
