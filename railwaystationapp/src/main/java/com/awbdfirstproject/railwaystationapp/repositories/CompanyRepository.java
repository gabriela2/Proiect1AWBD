package com.awbdfirstproject.railwaystationapp.repositories;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByIdentificationNumber(String identificationNumber);

    Optional<Company> findByName(String companyName);

    @Query(nativeQuery = true, value = "select name from company")
    List<String> findAllNames();
}
