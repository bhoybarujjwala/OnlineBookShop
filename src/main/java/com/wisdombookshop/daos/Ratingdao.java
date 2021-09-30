package com.wisdombookshop.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisdombookshop.entities.Rating;

public interface Ratingdao extends JpaRepository<Rating, Integer>{
	//SELECT bookId, AVG(rate) from rating GROUP BY BookId ORDER BY AVG(rate) DESC LIMIT 3;
	@Query(value = "SELECT r.bookId from rating r GROUP BY r.BookId ORDER BY AVG(r.rate) DESC LIMIT 10", nativeQuery = true)//todo--10
	List<Object[]> findtopRated();
	
	List<Rating> findByUserId(int userId);

	List<Rating> findByBookId(int bookId);

}
