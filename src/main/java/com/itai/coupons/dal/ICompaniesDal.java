package com.itai.coupons.dal;

import com.itai.coupons.dto.Company;
import com.itai.coupons.entities.CategoryEntity;
import com.itai.coupons.entities.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICompaniesDal extends CrudRepository<CompanyEntity, Integer> {

    @Query("SELECT new com.itai.coupons.dto.Company( c.companyName, c.id) FROM CompanyEntity c")
    List<Company> getAllCompanies();


    @Query("SELECT new com.itai.coupons.dto.Company( c.companyName, c.id) FROM CompanyEntity c WHERE c.companyName=:companyName")
    Company getCompanyIdByCompanyName(String companyName);
}
