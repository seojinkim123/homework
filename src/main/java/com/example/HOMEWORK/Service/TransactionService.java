package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Artifact;
import com.example.HOMEWORK.Entity.Transaction;
import com.example.HOMEWORK.Repository.TransactionRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    public Long saveTransaction(Transaction transaction){
        Transaction  savedTransaction=transactionRepository.save(transaction);
        return savedTransaction.getId();
    }

    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public Transaction findByIdTransaction(Long id) {
        return transactionRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }

}
