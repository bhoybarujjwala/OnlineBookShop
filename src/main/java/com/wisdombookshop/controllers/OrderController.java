package com.wisdombookshop.controllers;

import java.util.Date;
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

import com.wisdombookshop.entities.Address;
import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.OrderDetails;
import com.wisdombookshop.entities.PurchaseDetails;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.services.AddressService;
import com.wisdombookshop.services.BookService;
import com.wisdombookshop.services.OrderDetailsService;
import com.wisdombookshop.services.PurchaseDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop/order")
public class OrderController {
	
	@Autowired
	private AddressService addServ;
	
	@Autowired
	private OrderDetailsService orderServ;
	
	@Autowired
	private PurchaseDetailsService purchaseServ;
	
	@Autowired
	private BookService bookserv;
	
	@PostMapping("/add-order")
	public ResponseEntity<?> addOrder(OrderDetails odetails){
		return Response.success(orderServ.save(odetails));
	}
	
	@PutMapping("/update-total/{orderId}")
	public ResponseEntity<?> updateOrderTotal(@PathVariable("orderId") int orderId, OrderDetails newdetails){
		System.out.println(newdetails);
		OrderDetails orderdetails = orderServ.findById(orderId);
		System.out.println(orderdetails);
		orderdetails.setOtotal(newdetails.getOtotal());
		return Response.success(orderServ.save(orderdetails));
	}
		
	
	@PostMapping("/add-purchaseDetails/{orderId}")
	public ResponseEntity<?> addpurchaseDetails(@PathVariable("orderId") int orderId, PurchaseDetails pdetails){
		OrderDetails orderdetails = orderServ.findById(orderId);
		System.out.println("1"+pdetails);
		System.out.println("2"+orderdetails);
		if(orderdetails != null) {
			pdetails.setOrderdetails(orderdetails);
			Book book = bookserv.findBookById(pdetails.getBookId());
			int invent = book.getInventory();
			book.setInventory(invent - pdetails.getQty());//update inventory
			bookserv.save(book);
			return Response.success(purchaseServ.save(pdetails));
		}
		return Response.error("OrderId Not Found!!!");
		
		
	}
	
	@PostMapping("/add-address")
	public ResponseEntity<?> addAddress(Address address){
		return Response.success(addServ.save(address));
	}

	@GetMapping("/order-history/{userId}")
	public ResponseEntity<?> orderhistory(@PathVariable("userId") int userId){
		List<OrderDetails> orderlist = orderServ.findByUserId(userId);
		if(orderlist != null)
			return Response.success(orderlist);
		return Response.error("No orders yet");
	}
	
	@GetMapping("/purchase-details/{orderId}")
	public ResponseEntity<?> purchasedetails(@PathVariable("orderId") int orderId){
		List<PurchaseDetails> purchaselist =purchaseServ.findByOrderId(orderId);
		if(purchaselist != null)
			return Response.success(purchaselist);
		return Response.error("No details");
	}
	
	@GetMapping("/order-details/{orderId}")
	   public ResponseEntity<?> orderDetails(@PathVariable("orderId") int orderId){
	       OrderDetails order = orderServ.findById(orderId);
	       Date odate = order.getoDate();
	       return Response.success(odate);
	   }
	
}
