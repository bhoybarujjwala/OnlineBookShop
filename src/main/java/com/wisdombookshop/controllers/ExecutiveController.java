package com.wisdombookshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdombookshop.entities.EmpData;
import com.wisdombookshop.entities.OrderDetails;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.services.EmpDataService;
import com.wisdombookshop.services.OrderDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop/executive")
public class ExecutiveController{
	
	
	//change order status
	//Todo
	
	@Autowired
	private OrderDetailsService orderserv;
	
	
	@Autowired
	private EmpDataService dataServ;
	
	//apply for leaves
	@PostMapping("/leave-apply")
	public ResponseEntity<?> applyleaves(EmpData data ){
		EmpData executive = dataServ.findById(data.getUserId());
		if(executive != null) {
			executive.setLeavestatus(data.getLeavestatus());
			return Response.success(dataServ.save(executive));
		}
		return Response.error("Error");
	}
	
	//leave-status
	@GetMapping("/leave-status/{userid}")
	public ResponseEntity<?> leavestatus(@PathVariable("userid") int userid){
		EmpData data = dataServ.findById(userid);
		if(data != null) {
			return Response.success(data.getLeavestatus());
		}
		return Response.error("Error");
	}
	
	@GetMapping("/orders-list/{exeid}")
	public ResponseEntity<?> getallorders(@PathVariable("exeid") int exeid){
		//EmpData empdata = dataServ.findById(exeid);
		List<OrderDetails> orderlist = orderserv.findOrdersofExecutive(exeid, "undelivered");
		return Response.success(orderlist);
	}
	
	@GetMapping("/past-orders/{exeid}")
	public ResponseEntity<?> pastorders(@PathVariable("exeid") int exeid){
		//EmpData empdata = dataServ.findById(exeid);
		List<OrderDetails> orderlist = orderserv.findOrdersofExecutive(exeid, "delivered");
		return Response.success(orderlist);
	}
	
	@PutMapping("/update/{orderId}")
	public ResponseEntity<?> changestatus(@PathVariable("orderId") int orderId, OrderDetails details){
		OrderDetails oldorder = orderserv.findById(orderId);
		oldorder.setOrderStatus("delivered");
		oldorder.setDelDate(details.getDelDate());
		return Response.success(orderserv.save(oldorder));
	}
	
}
