package com.example.mini2.repositories;

import com.example.mini2.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    @Query(value = "SELECT * FROM captain WHERE avg_rating_score > :threshold", nativeQuery = true)
    public List<Captain> findCaptainsWithRating(@Param("threshold") double threshold);

    public Captain findByLicenseNumber(String licenseNumber);
}
