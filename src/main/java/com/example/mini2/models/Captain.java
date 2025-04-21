package com.example.mini2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "captain")
public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String licenseNumber;
    private double avg_rating_score;
    @OneToMany(mappedBy = "captain")
    @ToString.Exclude
    @JsonIgnore
    private List<Trip> trips;

    public Captain() {
    }

    public Captain(String name, String licenseNumber, double avg_rating_score) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avg_rating_score = avg_rating_score;
    }

    public Captain(String name, String licenseNumber, Double avg_rating_score, List<Trip> trips) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avg_rating_score = avg_rating_score;
        this.trips = trips;
    }

    public Captain(long id, String name, String licenseNumber, Double avg_rating_score, List<Trip> trips) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avg_rating_score = avg_rating_score;
        this.trips = trips;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public double getAvg_rating_score() {
        return avg_rating_score;
    }

    public void setAvg_rating_score(double avgRatingScore) {
        this.avg_rating_score = avgRatingScore;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
