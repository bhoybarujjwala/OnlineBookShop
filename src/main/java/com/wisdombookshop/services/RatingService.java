package com.wisdombookshop.services;

import java.util.List;

import com.wisdombookshop.entities.Rating;

public interface RatingService {

	Rating getRatingRow(int userId, int bookId);

	Rating save(Rating rating);

	List<Rating> findByBookId(int bookId);


}
