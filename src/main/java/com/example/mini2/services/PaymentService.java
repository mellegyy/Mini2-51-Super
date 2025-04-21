package com.example.mini2.services;

import com.example.mini2.models.Payment;
import com.example.mini2.models.Trip;
import com.example.mini2.repositories.PaymentRepository;
import com.example.mini2.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TripRepository tripRepository;

    public PaymentService(PaymentRepository paymentRepository, TripRepository tripRepository) {
        this.paymentRepository = paymentRepository;
        this.tripRepository = tripRepository;
    }

    public Payment addPayment(Payment payment) {
        if (payment.getTrip() != null && payment.getTrip().getId() != null) {
            Trip trip = tripRepository.findById(payment.getTrip().getId()).orElse(null);
            if (trip == null) {
                throw new IllegalArgumentException("Trip not found with id: " + payment.getTrip().getId());
            }
            payment.setTrip(trip);
        }
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id)
                .map(existing -> {
                    existing.setAmount(updatedPayment.getAmount());
                    existing.setPaymentMethod(updatedPayment.getPaymentMethod());
                    existing.setPaymentStatus(updatedPayment.getPaymentStatus());
                    existing.setTrip(updatedPayment.getTrip());
                    return paymentRepository.save(existing);
                })
                .orElse(null);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> findPaymentsByTripId(Long tripId) {
        Payment payment = paymentRepository.findByTripId(tripId);
        return payment != null ? List.of(payment) : List.of();
    }

    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}
