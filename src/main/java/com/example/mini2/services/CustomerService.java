
package com.example.mini2.services;

import com.example.mini2.models.Customer;
import com.example.mini2.models.Trip;
import com.example.mini2.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final TripService tripService;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, TripService tripService) {
        this.customerRepository = customerRepository;
        this.tripService = tripService;
    }
// Business logic methods here...

    public Customer addCustomer(Customer customer){
        if (customer.getName() == null || customer.getEmail() == null || customer.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Invalid customer data");
        }
//        Customer existingCustomerEmail = customerRepository.findByEmail(customer.getEmail());
//        Customer existingCustomerPhone = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
//        if (existingCustomerEmail != null || existingCustomerPhone != null) {
//            throw new RuntimeException("Customer already exists");
//        }
//        List<Trip> trips = tripService.getAllTrips();
//        for (Trip customerTrip : customer.getTrips()) {
//            boolean exists = false;
//            for (Trip trip : trips){
//                if(customerTrip.equals(trip)) {
//                    exists = true;
//                    break;
//                }
//            }
//            if (!exists) {
//                tripService.addTrip(customerTrip);
//            }
//        }
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        if (id == null) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Long id, Customer customer){
        List<Customer> customers = getAllCustomers();

        for (Customer c : customers) {
            if (c.getId() == id) {
                c.setName(customer.getName());
                c.setEmail(customer.getEmail());
                c.setPhoneNumber(customer.getPhoneNumber());
                c.setTrips(customer.getTrips());
                if (c.getName() == null || c.getEmail() == null || c.getPhoneNumber() == null) {
                    throw new IllegalArgumentException("Invalid customer data");
                }else {
                    customerRepository.saveAll(customers);
                    return c;
                }
            }
        }

        throw new RuntimeException("Customer not found");
    }

    public void deleteCustomer(Long id){
//        if (id == null) {
//            throw new IllegalArgumentException("Invalid customer ID");
//        }
//        if (!customerRepository.existsById(id)) {
//            throw new EntityNotFoundException("Customer not found");
//        }
        customerRepository.deleteById(id);
    }

    public List<Customer> findCustomersByEmailDomain(String domain){
        return customerRepository.findByEmailDomain(domain);
    }

    public List<Customer> findCustomersByPhonePrefix(String prefix){
        return customerRepository.findByPhonePrefix(prefix);
    }








}