package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Address;
import com.awbdfirstproject.railwaystationapp.repositories.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplementationTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImplementation addressServiceImplementation;

    @Test
    void findByElements() {
        Address address = new Address();
        address.setId(1);
        address.setNumber("10A");
        address.setStreet("Florilor");
        address.setCity("Bucuresti");
        address.setDistrict("Bucuresti");
        address.setZipcode("123456");
        when(addressRepository.findByElements("10A","Florilor","Bucuresti","Bucuresti","123456")).thenReturn(Optional.of(address));
        Optional<Address> result = addressServiceImplementation.findByElements("10A","Florilor","Bucuresti","Bucuresti","123456");
        assertEquals(address.getId(),result.get().getId());
    }

    @Test
    void save() {
        Address address = new Address();
        address.setId(1);
        address.setNumber("10A");
        address.setStreet("Florilor");
        address.setCity("Bucuresti");
        address.setDistrict("Bucuresti");
        address.setZipcode("123456");
        when(addressRepository.save(address)).thenReturn(address);
        Address result = addressServiceImplementation.save(address);
        assertEquals(address,result);
    }
}