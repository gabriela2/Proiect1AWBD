package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Address;

import java.util.Optional;

public interface AddressServiceInterface {
    Optional<Address> findByElements(String number, String street, String city, String district, String zipcode);

    Address save(Address address);
}
