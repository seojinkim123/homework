package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
