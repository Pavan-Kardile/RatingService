package com.micro.rating.service.impl;

import com.micro.rating.service.entities.Rating;
import com.micro.rating.service.exceptions.ResourceNotFoundException;
import com.micro.rating.service.repositories.RatingRepository;
import com.micro.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        logger.info("Creating rating for hotel: {}, user: {}", rating.getHotelId(), rating.getUserId());
        if (rating.getRatingId() == null || rating.getRatingId().isEmpty()) {
            rating.setRatingId(UUID.randomUUID().toString());
        }
        Rating savedRating = ratingRepository.save(rating);
        logger.info("Rating created successfully with ID: {}", savedRating.getRatingId());
        return savedRating;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        logger.info("Fetching ratings for user: {}", userId);
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        logger.info("Fetching ratings for hotel: {}", hotelId);
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRatings() {
        logger.info("Fetching all ratings");
        return ratingRepository.findAll();
    }
}

