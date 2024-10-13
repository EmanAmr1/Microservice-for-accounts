package com.microservices.accounts.repository;

import com.microservices.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo extends JpaRepository<Customer,Long> {
}
