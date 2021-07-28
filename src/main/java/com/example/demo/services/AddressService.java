package com.example.demo.services;


import com.example.demo.models.Address;
import com.example.demo.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.getById(id);
    }

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        Address update = addressRepository.getById((long) address.getId());
        if (update != null) {
            addressRepository.save(address);
            return update;
        }
        return address;
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}

