package com.wisdombookshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdombookshop.daos.Bookdao;
import com.wisdombookshop.daos.Ratingdao;
import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.EmpData;
import com.wisdombookshop.entities.Rating;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{ 
	@Autowired
	private Ratingdao dao;
	@Autowired
	private Bookdao bdao;
	//SELECT * from rating where userId = userId AND bookId = bookId;
	
	@Override
	public Rating getRatingRow(int userId, int bookId) {//1011, 501
		List<Rating> list2 = dao.findByBookId(bookId); //501 
		//System.out.println(list2);
		for (Rating rating : list2) {
			if(rating != null) {
				if(rating.getUserId() == userId) {
					//System.out.println(rating);
					return rating;
				}
			}
			
		}
		return null;
	}
	
	@Override
	public Rating save(Rating rating) {
		return dao.save(rating);
	}

	@Override
	public List<Rating> findByBookId(int bookId) {
		return dao.findByBookId(bookId);
	}

	public List<Book> findtoprated() {
		
		List<Book> newlist = new ArrayList<Book>();
			
		List<Object[]> list = dao.findtopRated();
		for (Object[] objects : list) {
			for (Object objects2 : objects) {
				
				int i = Integer.valueOf(String.valueOf(objects2));
				Optional<Book> b = bdao.findById(i);
				if(b != null) {
				newlist.add(b.orElse(null));
				}
			}
		}
		
		return newlist;
	}


}
