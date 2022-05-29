package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Address;
import com.awbdfirstproject.railwaystationapp.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImplementation implements AddressServiceInterface {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Optional<Address> findByElements(String number, String street, String city, String district, String zipcode) {
        log.info("The address was found");
        return addressRepository.findByElements(number, street, city, district, zipcode);
    }

    @Override
    public Address save(Address address) {
        log.info("The address was saved");
        return addressRepository.save(address);
    }
}
