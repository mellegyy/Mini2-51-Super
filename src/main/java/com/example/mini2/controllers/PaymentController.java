package com.example.mini2.controllers;

import com.example.mini2.models.Payment;
import com.example.mini2.services.PaymentService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            throw new ResourceNotFoundException("Payment with ID " + id + " not found.");
        }
        return payment;
    }

    @PutMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Payment existing = paymentService.getPaymentById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Payment with ID " + id + " not found.");
        }
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        if (paymentService.getPaymentById(id) == null) {
            return "Payment with ID " + id + " not found.";
        }
        paymentService.deletePayment(id);
        return "Payment with ID " + id + " has been deleted.";
    }

    @GetMapping("/findByTripId")
    public List<Payment> findPaymentsByTripId(@RequestParam Long tripId) {
        return paymentService.findPaymentsByTripId(tripId);
    }

    @GetMapping("/findByAmountThreshold")
    public List<Payment> findPaymentsWithAmountGreaterThan(@RequestParam Double threshold) {
        return paymentService.findByAmountThreshold(threshold);
    }
}
