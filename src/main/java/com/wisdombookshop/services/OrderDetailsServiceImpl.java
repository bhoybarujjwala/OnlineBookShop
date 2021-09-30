package com.wisdombookshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdombookshop.daos.EmpDataDao;
import com.wisdombookshop.daos.OrderDetailsdao;
import com.wisdombookshop.entities.Address;
import com.wisdombookshop.entities.OrderDetails;


@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private OrderDetailsdao dao;
	
	@Autowired
	private AddressService addserv;

	@Override
	public List<OrderDetails> findOrdersforCity(String city) {
		List<OrderDetails> orderlist = new ArrayList<OrderDetails>();
		List<Address> list = addserv.findByCity(city);
		System.out.println("list"+list);
		for (Address address : list) {
			int id = address.getUserId();
			System.out.println(id);
			 List<OrderDetails> orderobject = dao.findByUserId(id);
			 System.out.println(orderobject);
			 for (OrderDetails order : orderobject) {
				 if(order.getOrderStatus().equals("undelivered"))
					 orderlist.add(order);
				 System.out.println("orderlist"+orderlist);
			}
			 
		}
		return orderlist;
	}

	@Override
	public OrderDetails save(OrderDetails odetails) {
		return dao.save(odetails);
	}

	@Override
	public OrderDetails findById(int orderId) {
		return dao.getById(orderId);
	}

	@Override
	public List<OrderDetails> findOrdersofExecutive(int exeid, String status) {
		List<OrderDetails> neworderlist = new ArrayList<OrderDetails>();
		List<OrderDetails> oldlist = dao.findByDelExeId(exeid);
		for (OrderDetails order : oldlist) {
			 if(order.getOrderStatus().equals(status))
				 neworderlist.add(order);
			 System.out.println("neworderlist"+neworderlist);
		}
		return neworderlist;
	}

	@Override
	public List<OrderDetails> findByUserId(int userId) {
		
		return dao.findByUserId(userId);
	}
	
}
