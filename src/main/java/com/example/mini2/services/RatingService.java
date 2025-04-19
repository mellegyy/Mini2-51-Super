package com.example.mini2.services;

import com.example.mini2.models.Rating;
import com.example.mini2.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // Add a new rating
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    // Update an existing rating
    public Rating updateRating(String id, Rating updatedRating) {
        Optional<Rating> existingRatingOpt = ratingRepository.findById(id);
        if (existingRatingOpt.isPresent()) {
            Rating existing = existingRatingOpt.get();
            existing.setScore(updatedRating.getScore());
            existing.setComment(updatedRating.getComment());
            existing.setRatingDate(updatedRating.getRatingDate());
            return ratingRepository.save(existing);
        }
        return null;
    }

    // Delete a rating
    public void deleteRating(String id) {
        ratingRepository.deleteById(id);
    }

    // Find ratings by entity ID and type
    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    // Find ratings with score above a threshold
    public List<Rating> findRatingsAboveScore(int minScore) {
        return ratingRepository.findByScoreGreaterThan(minScore);
    }
}
