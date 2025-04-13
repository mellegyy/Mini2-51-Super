package com.example.mini2.repositories;

import com.example.mini2.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find a payment by the associated trip ID
   // Payment findByTripId(Long tripId);

    // Find all payments where the amount is greater than a given value
    List<Payment> findByAmountGreaterThan(Double amount);
}
