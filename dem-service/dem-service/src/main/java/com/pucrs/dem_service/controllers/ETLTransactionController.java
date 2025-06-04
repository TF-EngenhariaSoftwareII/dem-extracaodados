package com.pucrs.dem_service.controllers;

import org.springframework.web.bind.annotation.*;

import com.pucrs.dem_service.entities.ETLTransaction;
import com.pucrs.dem_service.services.ETLTransactionService;

import java.util.List;

@RestController
@RequestMapping("/etl/transactions")
public class ETLTransactionController {

    private final ETLTransactionService etlTransactionService;

    public ETLTransactionController(ETLTransactionService etlTransactionService) {
        this.etlTransactionService = etlTransactionService;
    }

   
    @GetMapping
    public List<ETLTransaction> getAllTransactions() {
        return etlTransactionService.getAllTransactions();
    }


}
