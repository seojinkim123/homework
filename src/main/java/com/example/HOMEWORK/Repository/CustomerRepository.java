package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
