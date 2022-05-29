package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.dto.CompanyDto;

import java.util.List;

public interface CompanyServiceInterface {
    Company save(Company user);

    List<Company> findAll();

    void deleteById(Long id);

    void update(Long id, CompanyDto companyDto);

    Company findById(Long id);

    Company findByName(String companyName);

    List<String> findAllByName();
}
