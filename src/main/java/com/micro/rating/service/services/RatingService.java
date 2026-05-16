package com.micro.rating.service.services;

import com.micro.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get by userId
    List<Rating> getRatingByUserId(String userId);

    //get by hotelId
    List<Rating> getRatingByHotelId(String hotelId);

    //getAll
    List<Rating> getAllRatings();

}
