package com.wisdombookshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdombookshop.daos.OrderDetailsdao;
import com.wisdombookshop.daos.PurchaseDetailsdao;
import com.wisdombookshop.entities.OrderDetails;
import com.wisdombookshop.entities.PurchaseDetails;


@Service
@Transactional
public class PurchaseDetailsServiceImpl implements PurchaseDetailsService {

	@Autowired
	private PurchaseDetailsdao dao;
	
	@Override
	public PurchaseDetails save(PurchaseDetails pdetails) {
		return dao.save(pdetails);
	}

	@Override
	public List<PurchaseDetails> findByOrderId(int orderId) {
		List<PurchaseDetails> completelist = dao.findAll();
		List<PurchaseDetails> newlist = new ArrayList<PurchaseDetails>();
		
		for (PurchaseDetails purchaseDetails : completelist) {
			if(purchaseDetails != null) {
				if(purchaseDetails.getOrderdetails().getOrderId() == orderId) {
					newlist.add(purchaseDetails);
				}
			}
		}
		
		return newlist;
	}

	@Override
	public List<PurchaseDetails> findAll() {
	List<PurchaseDetails> purchaselist = dao.findAll();
		return purchaselist;
	}
	
	
}
