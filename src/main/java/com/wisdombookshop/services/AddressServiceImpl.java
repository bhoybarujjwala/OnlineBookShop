package com.wisdombookshop.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdombookshop.daos.AddressDao;

import com.wisdombookshop.entities.Address;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{
    @Autowired
	private AddressDao aDao;
	@Override
	public Address findById(int id) {
		Optional<Address> address=aDao.findById(id);
		return address.orElse(null);
	}
	
	@Override
	public List<Address> findByCity(String city) {
		  List<Address> addresslist = aDao.findByCity(city);
		return addresslist;
	}

	@Override
	public Address save(Address address) {
		return aDao.save(address);
	}
	
	

}
