package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.domain.Journey;
import com.awbdfirstproject.railwaystationapp.dto.CompanyDto;
import com.awbdfirstproject.railwaystationapp.exception.ResourceAlreadyExistsException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeDeletedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceCannotBeUpdatedException;
import com.awbdfirstproject.railwaystationapp.exception.ResourceNotFoundException;
import com.awbdfirstproject.railwaystationapp.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplementationTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyServiceImplementation companyServiceImplementation;

    @Test
    void testSaveAFoundCompany() {
        Company company =new Company();
        company.setIdentificationNumber("123");
        company.setName("CFR");
        when(companyRepository.findByIdentificationNumber("123")).thenReturn(Optional.of(company));
        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class,()->companyServiceImplementation.save(company));
        assertEquals("In baza de date exista deja resursa CFR!", exception.getMessage());
    }

    @Test
    void testSaveANewCompany() {
        Company company =new Company();
        company.setIdentificationNumber("123");
        company.setName("CFR");
        when(companyRepository.findByIdentificationNumber("123")).thenReturn(Optional.empty());
        when(companyRepository.save(company)).thenReturn(company);
        Company result = companyServiceImplementation.save(company);
        assertEquals(result.getIdentificationNumber(),company.getIdentificationNumber());
    }

    @Test
    void findAll() {
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        companyList.add(company);
        when(companyRepository.findAll()).thenReturn(companyList);
        List<Company> result = companyServiceImplementation.findAll();
        assertEquals(companyList,result);

    }

    @Test
    void deleteByIdWhenIdNotFound() {
        Company company = new Company();
        company.setId(1L);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->companyServiceImplementation.deleteById(anyLong()));
        assertEquals("Resursa companie cu id-ul 0 nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void deleteByIdWhenIdFoundAndListJourneysNotEmpty() {
        Company company = new Company();
        company.setId(1L);
        List<Journey> journeys = new ArrayList<>();
        Journey journey = new Journey();
        journeys.add(journey);
        company.setJourneys(journeys);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        ResourceCannotBeDeletedException exception = assertThrows(ResourceCannotBeDeletedException.class,()->companyServiceImplementation.deleteById(1L));
        assertEquals("Resursa companie cu id-ul 1 nu poate fi stearsa!", exception.getMessage());
    }

    @Test
    void deleteByIdWhenIdFoundAndListJourneysEmpty() {
        Company company = new Company();
        company.setId(1L);
        List<Journey> journeys = new ArrayList<>();
        company.setJourneys(journeys);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        companyServiceImplementation.deleteById(1l);
        verify(companyRepository).deleteById(1l);
        }


    @Test
    void updateWhenCompanyNotFound() {
        CompanyDto company = new CompanyDto();
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->companyServiceImplementation.update(anyLong(),company));
        assertEquals("Resursa companie cu id-ul 0 nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void updateWhenIdentificationNumberNotEqual() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setIdentificationNumber("123");
        companyDto.setName("CFR");
        Company company = new Company();
        company.setIdentificationNumber("122");
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        ResourceCannotBeUpdatedException exception = assertThrows(ResourceCannotBeUpdatedException.class,()->companyServiceImplementation.update(1l,companyDto));
        assertEquals("Resursa companie cu id-ul 1 nu poate fi actualizata!", exception.getMessage());
    }

    @Test
    void updateWhenIdentificationNumberEqual() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setIdentificationNumber("123");
        companyDto.setName("CFR");
        Company company = new Company();
        company.setId(1l);
        company.setIdentificationNumber("123");
        company.setName(companyDto.getName());

        when(companyRepository.findById(1l)).thenReturn(Optional.of(company));
        companyServiceImplementation.update(1l,companyDto);
        Optional<Company> result = companyRepository.findById(1l);
        assertEquals(result.get().getName(),company.getName());
    }



    @Test
    void findByIdNotFound() {
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->companyServiceImplementation.findById(anyLong()));
        assertEquals("Resursa companie cu id-ul 0 nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void findByIdFound() {
        Company company = new Company();
        company.setId(1l);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        Company result = companyServiceImplementation.findById(1l);
        assertEquals(company,result);
    }

    @Test
    void findByNameNotFound() {
        when(companyRepository.findByName(anyString())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->companyServiceImplementation.findByName("a"));
        assertEquals("Resursa companie cu numele a nu poate fi gasita!", exception.getMessage());
    }

    @Test
    void findByNameFound() {
        Company company = new Company();
        company.setName("a");
        when(companyRepository.findByName(anyString())).thenReturn(Optional.of(company));
        Company result = companyServiceImplementation.findByName("a");
        assertEquals(company,result);
    }

    @Test
    void findAllByName() {
        List<String> companyList = new ArrayList<>();
        Company company = new Company();
        company.setName("comp1");
        companyList.add(company.getName());
        when(companyRepository.findAllNames()).thenReturn(companyList);
        List<String> result = companyServiceImplementation.findAllByName();
        assertEquals(companyList,result);
    }
}