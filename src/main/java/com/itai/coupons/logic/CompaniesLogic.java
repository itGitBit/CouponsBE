package com.itai.coupons.logic;

import com.itai.coupons.dal.ICompaniesDal;
import com.itai.coupons.dto.Company;
import com.itai.coupons.entities.CompanyEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesLogic {
    private ICompaniesDal companiesDal;

    @Autowired
    public CompaniesLogic(ICompaniesDal companiesDal) {
        this.companiesDal = companiesDal;
    }

    public int createCompany(Company company) throws ApplicationException {
        validateCompanyName(company.getCompanyName());
        CompanyEntity companyEntity = new CompanyEntity(company);
        this.companiesDal.save(companyEntity);
        StatisticsUtils.sendStatistics("Company added, company: " + company.getId());
        return company.getId();
    }

    public void removeCompany(int companyId) throws ApplicationException {
        this.companiesDal.deleteById(companyId);
        StatisticsUtils.sendStatistics("Company added, company: " + companyId);
    }

    public Company getCompany(int companyId) throws ApplicationException {
        CompanyEntity companyEntity = this.companiesDal.findById(companyId).get();
        Company company = new Company(companyEntity);
        StatisticsUtils.sendStatistics("Company read, Company: " + companyId);
        return company;
    }

    public void updateCompany(Company company) throws ApplicationException {
        validateCompanyName(company.getCompanyName());
        CompanyEntity companyEntity = new CompanyEntity(company);
        companiesDal.save(companyEntity);
        StatisticsUtils.sendStatistics("Company updated, company: " + company.getId());
    }

    public List<Company> getAllCompanies() throws ApplicationException {
        return companiesDal.getAllCompanies();
    }


    private void validateCompanyName(String companyName) throws ApplicationException {
        if (companyName.length() < 2) {
            throw new ApplicationException(ErrorType.NAME_TOO_SHORT);
        }
        if (companyName.length() > 25) {
            throw new ApplicationException(ErrorType.NAME_TOO_LONG);
        }
    }

    boolean isCompanyIdExist(int companyId) throws ApplicationException {
        return companiesDal.existsById(companyId);
    }

    public int getCompanyIdByCompanyName(String companyName) {
        return companiesDal.getCompanyIdByCompanyName(companyName).getId();
    }
}



