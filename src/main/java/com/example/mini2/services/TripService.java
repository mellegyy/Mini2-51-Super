package com.example.mini2.services;

import com.example.mini2.models.Trip;
import com.example.mini2.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
        Optional<Trip> existingTripOpt = tripRepository.findById(id);
        if (existingTripOpt.isPresent()) {
            Trip existingTrip = existingTripOpt.get();
            existingTrip.setTripDate(updatedTrip.getTripDate());
            existingTrip.setOrigin(updatedTrip.getOrigin());
            existingTrip.setDestination(updatedTrip.getDestination());
            existingTrip.setTripCost(updatedTrip.getTripCost());
            existingTrip.setCaptain(updatedTrip.getCaptain());
            existingTrip.setCustomer(updatedTrip.getCustomer());
            return tripRepository.save(existingTrip);
        }
        return null;
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return tripRepository.findByTripDateBetween(startDate, endDate);
    }

    public List<Trip> findTripsByCaptainId(Long captainId) {
        return tripRepository.findByCaptainId(captainId);
    }
}
