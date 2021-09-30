package com.wisdombookshop.services;

import java.util.List;

import com.wisdombookshop.entities.Address;

public interface AddressService {
	Address findById(int id);
	List<Address> findByCity(String city);
	Address save(Address address);
}
