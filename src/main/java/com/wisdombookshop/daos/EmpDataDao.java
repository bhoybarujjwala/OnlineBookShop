package com.wisdombookshop.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdombookshop.entities.EmpData;

public interface EmpDataDao extends JpaRepository<EmpData, Integer> {

}
