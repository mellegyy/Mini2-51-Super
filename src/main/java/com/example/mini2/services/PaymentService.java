package com.example.mini2.services;

import com.example.mini2.models.Payment;
import com.example.mini2.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // 8.4.2.1 Add Payment
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // 8.4.2.2 Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // 8.4.2.3 Get Payment By ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // 8.4.2.4 Update Payment

    public Payment updatePayment(Long id, Payment updatedPayment) {
     /*   Optional<Payment> existing = paymentRepository.findById(id);
        if (existing.isPresent()) {
            Payment payment = existing.get();
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentMethod(updatedPayment.getPaymentMethod());
            payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            payment.setTrip(updatedPayment.getTrip());
            return paymentRepository.save(payment);
        } else {
            return null;
        }
     */
        return  paymentRepository.save(updatedPayment); //dummy return
    }

    // 8.4.2.5 Delete Payment
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // 8.4.2.6 Find Payments By Trip ID
    public List<Payment> findPaymentsByTripId(Long tripId) {
        // Since findByTripId returns a single payment, wrap it into a list if found
        Payment payment = paymentRepository.findByTripId(tripId);
        return payment != null ? List.of(payment) : List.of();
    }

    // 8.4.2.7 Find Payments With an Amount Greater Than a Threshold
    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}
