package com.micro.rating.service.controllers;

import com.micro.rating.service.entities.Rating;
import com.micro.rating.service.exceptions.ApiResponse;
import com.micro.rating.service.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@Tag(name = "Rating Management", description = "APIs for managing hotel ratings")
public class RatingController {

    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @PostMapping
    @Operation(summary = "Create a new rating", description = "Create and save a new rating for a hotel")
    public ResponseEntity<ApiResponse> create(@RequestBody Rating rating) {
        logger.info("Creating rating for hotel: {}, user: {}", rating.getHotelId(), rating.getUserId());
        Rating createdRating = ratingService.create(rating);
        ApiResponse response = new ApiResponse("Rating created successfully", true, createdRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all ratings", description = "Retrieve all ratings from the database")
    public ResponseEntity<ApiResponse> getAllRatings() {
        logger.info("Fetching all ratings");
        List<Rating> ratings = ratingService.getAllRatings();
        ApiResponse response = new ApiResponse("Ratings retrieved successfully", true, ratings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hotel/{hotelId}")
    @Operation(summary = "Get ratings by hotel ID", description = "Fetch all ratings for a specific hotel")
    public ResponseEntity<ApiResponse> getByHotelId(@PathVariable String hotelId) {
        logger.info("Fetching ratings for hotel: {}", hotelId);
        List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
        ApiResponse response = new ApiResponse("Hotel ratings retrieved successfully", true, ratings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get ratings by user ID", description = "Fetch all ratings given by a specific user")
    public ResponseEntity<ApiResponse> getByUserId(@PathVariable String userId) {
        logger.info("Fetching ratings from user: {}", userId);
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        ApiResponse response = new ApiResponse("User ratings retrieved successfully", true, ratings);
        return ResponseEntity.ok(response);
    }

}
