package com.wisdombookshop.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdombookshop.entities.Address;

public interface AddressDao extends JpaRepository<Address, Integer>{
    List<Address> findByCity(String city);
}
