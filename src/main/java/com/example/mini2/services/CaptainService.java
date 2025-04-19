package com.example.mini2.services;

import com.example.mini2.models.Captain;
import com.example.mini2.repositories.CaptainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaptainService {
    // Dependency Injection
    private final CaptainRepository captainRepository;
    public CaptainService(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }

    public Captain addCaptain(Captain captain){
        if (captain.getName() == null || captain.getAvg_rating_score() < 0 || captain.getLicenseNumber() == null) {
            throw new IllegalArgumentException("Invalid captain data");
        }
        Captain existingCaptain = captainRepository.findByLicenseNumber(captain.getLicenseNumber());
        if (existingCaptain != null) {
            throw new RuntimeException("Captain with the same license number already exists");
        }
        return captainRepository.save(captain);
    }

    public List<Captain> getAllCaptains(){
        return captainRepository.findAll();
    }

    public Captain getCaptainById(Long id){
        if (id == null) {
            throw new IllegalArgumentException("Invalid captain ID");
        }
        return captainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Captain not found"));}

    public List<Captain> getCaptainsByRating(Double ratingThreshold){
        if (ratingThreshold == null) {
            throw new IllegalArgumentException("Invalid rating.");
        }
        List<Captain> captains = captainRepository.findCaptainsWithRating(ratingThreshold);
        if (captains.isEmpty()) {
            throw new IllegalArgumentException("No Captains found");
        }
        return captains;
    }

    public Captain getCaptainByLicenseNumber(String licenseNumber){
        if (licenseNumber == null) {
            throw new IllegalArgumentException("Invalid license number.");
        }
        Captain captain = captainRepository.findByLicenseNumber(licenseNumber);
        if (captain == null) {
            throw new IllegalArgumentException("Captain not found");
        }
        return captain;
    }



}
