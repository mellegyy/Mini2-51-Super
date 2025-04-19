package com.example.mini2.repositories;

import com.example.mini2.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    // Find by entityId and entityType
    List<Rating> findByEntityIdAndEntityType(Long entityId, String entityType);

    // Find ratings with a score above a certain value
    List<Rating> findByScoreGreaterThan(int score);
}
