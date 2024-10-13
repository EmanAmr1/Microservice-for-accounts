package com.microservices.accounts.repository;

import com.microservices.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountRepo extends JpaRepository<Accounts,Long> {
}
