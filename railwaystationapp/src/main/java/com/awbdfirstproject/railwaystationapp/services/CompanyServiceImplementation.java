package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.dto.CompanyDto;
import com.awbdfirstproject.railwaystationapp.exception.ResourceAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeUpdatedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CompanyServiceImplementation implements CompanyServiceInterface {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company save(Company newCompany) {
        Optional<Company> company = companyRepository.findByIdentificationNumber(newCompany.getIdentificationNumber());
        if (company.isPresent()) {
            log.info("The company is already present in the db.");
            throw new ResourceAlreadyExistsException(newCompany.getName());
        }
        log.info("Comany " + newCompany.getName() + " was saved");
        return companyRepository.save(newCompany);
    }

    @Override
    public List<Company> findAll() {
        log.info("Return all the companies present in the db");
        return companyRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("companie cu id-ul " + id));
        if (company.getJourneys().isEmpty()) {
            log.info("The company with id = " + id + "was deleted");
            companyRepository.deleteById(id);
        } else {
            log.info("The company " + company.getName() + " cannot be deleted");
            throw new ResourceCannotBeDeletedException("companie cu id-ul " + id);
        }
    }

    @Override
    public void update(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("companie cu id-ul " + id));

        if (companyDto.getIdentificationNumber().compareTo(company.getIdentificationNumber()) == 0) {
            company.setName(companyDto.getName());
            companyRepository.save(company);
            log.info("Company " + company.getName() + " was updated");
        } else {
            log.info("The company " + company.getName() + " cannot be update");
            throw new ResourceCannotBeUpdatedException("companie cu id-ul " + id);
        }
    }

    @Override
    public Company findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("companie cu id-ul " + id));
        log.info("Return the company with id " + id);
        return company;
    }

    @Override
    public Company findByName(String companyName) {
        Company company = companyRepository.findByName(companyName).orElseThrow(() -> new ResourceNotFoundException("companie cu numele " + companyName));
        log.info("Return the company with name " + companyName);
        return company;
    }

    @Override
    public List<String> findAllByName() {
        log.info("Return all the companies from db.");
        return companyRepository.findAllNames();
    }


}
