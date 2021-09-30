package com.wisdombookshop.controllers;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdombookshop.entities.Rating;
import com.wisdombookshop.models.Credential;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.services.RatingServiceImpl;
import com.wisdombookshop.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop/feedback")
public class RatingController {
	@Autowired
	private UserService userServ;
	
	@Autowired
	private RatingServiceImpl rateServ;
	
	//Rating by user
	@PostMapping("/add-rating")
	public ResponseEntity<?> customerfeedback(Rating newrating){
		//System.out.println(newrating);
		Rating availablerow = rateServ.getRatingRow(newrating.getUserId(), newrating.getBookId());
		if(availablerow != null)
			return Response.error("Already Rated");
		return Response.success(rateServ.save(newrating));
	}
	
	//Get-rating-for-book
	@GetMapping("/get-rating/{bookId}")
	public ResponseEntity<?> getbookrating(@PathVariable("bookId") int bookId){
		double avgRating=0;
		double total=0;
		List<Rating>list = rateServ.findByBookId(bookId);
		for (Rating rating : list) {
			if(rating != null) {
				total = total + rating.getRate();
			}
		}
		avgRating = total/(list.size());
		return Response.success(avgRating);
	}
	
	//Get-rating-for-book
	@GetMapping("/get-reviews/{bookId}")
	public ResponseEntity<?> getbookreviws(@PathVariable("bookId") int bookId){
		List<Rating>list = rateServ.findByBookId(bookId);
		if(list != null) {
			return Response.success(list);
		}
		return Response.error("No Reviews yet!!");
	}

	
}
