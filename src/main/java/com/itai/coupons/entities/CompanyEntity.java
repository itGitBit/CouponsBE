package com.itai.coupons.entities;

import com.itai.coupons.dto.Company;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="company_name",nullable = false,unique = true)
    private String companyName;

    @OneToMany(mappedBy = "company")
    private List<CouponEntity> couponsList;

    @OneToMany(mappedBy = "company",cascade = CascadeType.REMOVE)
    private List<UserEntity> employeeList;


    public CompanyEntity() {
    }


    public CompanyEntity(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public CompanyEntity(Company company) {
        this.companyName = company.getCompanyName();
        this.id = company.getId();
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<UserEntity> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<UserEntity> employeeList) {
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CouponEntity> getCouponsList() {
        return couponsList;
    }

    public void setCouponsList(List<CouponEntity> couponsList) {
        this.couponsList = couponsList;
    }
}
