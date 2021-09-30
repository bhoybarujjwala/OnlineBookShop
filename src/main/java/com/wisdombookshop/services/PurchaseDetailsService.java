package com.wisdombookshop.services;

import java.util.List;

import com.wisdombookshop.entities.PurchaseDetails;

public interface PurchaseDetailsService {

	PurchaseDetails save(PurchaseDetails pdetails);

	List<PurchaseDetails> findByOrderId(int orderId);

	List<PurchaseDetails> findAll();
	
}
