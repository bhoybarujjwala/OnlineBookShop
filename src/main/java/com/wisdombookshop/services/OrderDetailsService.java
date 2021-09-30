package com.wisdombookshop.services;

import java.util.List;

import com.wisdombookshop.entities.OrderDetails;

public interface OrderDetailsService {

	List<OrderDetails> findOrdersforCity(String city);

	OrderDetails save(OrderDetails odetails);

	OrderDetails findById(int orderId);

	List<OrderDetails> findOrdersofExecutive(int exeid, String status);

	List<OrderDetails> findByUserId(int userId);
}
