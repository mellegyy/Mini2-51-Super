package com.example.mini2.repositories;

import com.example.mini2.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByTripDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Trip> findByCaptainId(Long captainId);
}
