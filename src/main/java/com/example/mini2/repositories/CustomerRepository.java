
package com.example.mini2.repositories;

import com.example.mini2.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value ="SELECT * FROM customers WHERE email LIKE CONCAT('%@', :domain)", nativeQuery = true)
    List<Customer> findByEmailDomain(@Param("domain") String domain);

    @Query(value ="SELECT * FROM customers WHERE phone_number LIKE CONCAT(:prefix, '%')", nativeQuery = true)
    List<Customer> findByPhonePrefix(@Param("prefix") String prefix);

    Customer findByEmail(String email);

    Customer findByPhoneNumber(String phoneNumber);
}