package com.example.mini2.services;

import com.example.mini2.models.Captain;
import com.example.mini2.models.Trip;
import com.example.mini2.repositories.CaptainRepository;
import com.example.mini2.repositories.TripRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaptainService {
    // Dependency Injection
    private final CaptainRepository captainRepository;
    private final TripService tripService;
    public CaptainService(CaptainRepository captainRepository, TripService tripService) {
        this.captainRepository = captainRepository;
        this.tripService = tripService;
    }

    public Captain addCaptain(Captain captain){
//        if(captain == null){
//            throw new InvalidDataAccessApiUsageException("Invalid captain data");
//        }
//        if (captain.getName() == null || captain.getAvg_rating_score() < 0 || captain.getLicenseNumber() == null) {
//            throw new IllegalArgumentException("Invalid captain data");
//        }
//        Captain existingCaptain = captainRepository.findByLicenseNumber(captain.getLicenseNumber());
//        if (existingCaptain != null) {
//            throw new RuntimeException("Captain with the same license number already exists");
//        }
        return captainRepository.save(captain);
    }

    public List<Captain> getAllCaptains(){
        return captainRepository.findAll();
    }

    public Captain getCaptainById(Long id){
//        if (id == null) {
//            throw new InvalidDataAccessApiUsageException("Invalid captain ID");
//        }
        return captainRepository.findById(id).orElse(null);
//                .orElseThrow(() -> new RuntimeException("Captain not found"));
    }

    public List<Captain> getCaptainsByRating(Double ratingThreshold){
//        if (ratingThreshold == null) {
//            throw new IllegalArgumentException("Invalid rating.");
//        }
        List<Captain> captains = captainRepository.findCaptainsWithRating(ratingThreshold);
//        if (captains.isEmpty()) {
//            throw new IllegalArgumentException("No Captains found");
//        }
        return captains;
    }

    public Captain getCaptainByLicenseNumber(String licenseNumber) {
//        if (licenseNumber == null) {
//            throw new IllegalArgumentException("Invalid license number.");
//        }
        return captainRepository.findByLicenseNumber(licenseNumber); // Return null if not found
    }



}
