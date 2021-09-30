package com.wisdombookshop.services;

import com.wisdombookshop.entities.EmpData;

public interface EmpDataService {
	EmpData save(EmpData data);
	EmpData findById(int id);
	Boolean deleteByid(int userid);
	
}
