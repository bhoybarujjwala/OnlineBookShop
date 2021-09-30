package com.wisdombookshop.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdombookshop.daos.EmpDataDao;
import com.wisdombookshop.entities.EmpData;

@Service
@Transactional
public class EmpDataServiceImpl implements EmpDataService {
	
	@Autowired
	private EmpDataDao empdao;

	@Override
	public EmpData save(EmpData data) {
		return empdao.save(data);
	}

	@Override
	public EmpData findById(int id) {
		Optional<EmpData> data = empdao.findById(id);
		return data.orElse(null);
	}

	@Override
	public Boolean deleteByid(int userid) {
		if(empdao.existsById(userid)) {
			empdao.deleteById(userid);
			return true;
		}
		return false;
	}
	
}
