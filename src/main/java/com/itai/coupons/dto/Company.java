package com.itai.coupons.dto;

import com.itai.coupons.entities.CompanyEntity;

public class Company {
    private String companyName;
    private int id;

    public Company() {
    }

    public Company(CompanyEntity company) {
        this.companyName = company.getCompanyName();
        this.id = company.getId();
    }

    public Company(String companyName) {
        this.companyName = companyName;

    }

    public Company(String companyName, int id) {
        this.companyName = companyName;
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", companyId=" + id +
                '}';
    }
}

