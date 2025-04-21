package com.example.mini2.repositories;

import com.example.mini2.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByTripId(Long tripId);

    List<Payment> findByAmountGreaterThan(Double amount);
}
