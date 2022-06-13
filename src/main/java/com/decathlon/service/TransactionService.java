package com.decathlon.service;

import com.decathlon.enums.Status;
import com.decathlon.model.Transaction;
import com.decathlon.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    public Transaction createTransaction(Transaction transaction) {
        transaction.setStatus(Status.NEW);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> modifyStatus(long id, Status status) {
        return transactionRepository.findById(id)
            .filter(transaction -> checkIfPossibleToChangeStatus(transaction, status))
            .map(transaction -> changeStatusTransaction(transaction, status))
            .map(transactionRepository::save);
    }

    private Transaction changeStatusTransaction(Transaction transaction, Status status) {
        transaction.setStatus(status);
        return transaction;
    }

    private boolean checkIfPossibleToChangeStatus(Transaction transaction, Status status) {
        if(Status.CAPTURED.equals(status)) {
            return Status.AUTHORIZED.equals(transaction.getStatus());
        } else
            return !Status.CAPTURED.equals(transaction.getStatus());
    }
}
