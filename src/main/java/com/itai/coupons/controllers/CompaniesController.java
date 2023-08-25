package com.itai.coupons.controllers;

import com.itai.coupons.dto.Company;
import com.itai.coupons.entities.CompanyEntity;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.logic.CompaniesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private CompaniesLogic companiesLogic;

    @Autowired
    public CompaniesController(CompaniesLogic companiesLogic) {
        this.companiesLogic = companiesLogic;
    }

    @PostMapping
    public void createCompany(@RequestBody Company company) throws ApplicationException {
        companiesLogic.createCompany(company);
    }

    @PutMapping
    public void updateCompany(@RequestBody Company company) throws ApplicationException {
        companiesLogic.updateCompany(company);
    }
    @GetMapping
    public List<Company> getAllCompanies() throws ApplicationException {
        List<Company> companies = companiesLogic.getAllCompanies();
        return companies;
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable("companyId") int companyId) throws ApplicationException {
        Company company = companiesLogic.getCompany(companyId);
        return company;
    }

    @DeleteMapping("/{companyId}")
    public void removeCompany(@PathVariable("companyId") int companyId) throws ApplicationException {
        companiesLogic.removeCompany(companyId);
    }




}
