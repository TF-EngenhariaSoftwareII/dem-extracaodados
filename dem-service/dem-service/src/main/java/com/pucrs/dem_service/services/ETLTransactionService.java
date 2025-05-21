package com.pucrs.dem_service.services;

import org.springframework.stereotype.Service;

import com.pucrs.dem_service.entities.ETLTransaction;
import com.pucrs.dem_service.repositories.ETLTransactionRepo;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class ETLTransactionService {

    private final ETLTransactionRepo repository;

    public ETLTransactionService(ETLTransactionRepo repository) {
        this.repository = repository;
    }

    public ETLTransaction startTransaction() {
        ETLTransaction etl = new ETLTransaction();
        etl.setStartedAt(LocalDateTime.now());
        etl.setStatus("IN_PROGRESS");
        return repository.save(etl);
    }

    public void finishTransactionSuccess(Long id) {
        ETLTransaction etl = repository.findById(id).orElseThrow();
        etl.setEndedAt(LocalDateTime.now());
        etl.setStatus("SUCCESS");
        repository.save(etl);
    }

    public void finishTransactionFailed(Long id, String errorMessage) {
        ETLTransaction etl = repository.findById(id).orElseThrow();
        etl.setEndedAt(LocalDateTime.now());
        etl.setStatus("FAILED");
        etl.setErrorMessage(errorMessage);
        repository.save(etl);
    }

    public List<ETLTransaction> getAllTransactions() {
        return repository.findAll();
    }

}


