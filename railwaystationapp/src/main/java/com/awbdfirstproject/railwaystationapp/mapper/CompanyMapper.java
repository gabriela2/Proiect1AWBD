package com.awbdfirstproject.railwaystationapp.mapper;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company companyDtoToCompany(CompanyDto companyDto) {
        return new Company(companyDto.getName(), companyDto.getIdentificationNumber());
    }

    public CompanyDto companyToCompanyDto(Company company) {
        return new CompanyDto(company.getName(), company.getIdentificationNumber());
    }
}
