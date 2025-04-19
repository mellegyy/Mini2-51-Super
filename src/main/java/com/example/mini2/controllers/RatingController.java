package com.example.mini2.controllers;

import com.example.mini2.models.Rating;
import com.example.mini2.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Add a new rating
    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    // Update a rating
    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }

    // Delete a rating
    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return "Rating with ID " + id + " deleted successfully.";
    }

    // Get ratings by entity ID and type
    @GetMapping("/findByEntity")
    public List<Rating> getRatingsByEntity(
            @RequestParam Long entityId,
            @RequestParam String entityType
    ) {
        return ratingService.getRatingsByEntity(entityId, entityType);
    }

    // Get ratings with score above threshold
    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore) {
        return ratingService.findRatingsAboveScore(minScore);
    }
}
