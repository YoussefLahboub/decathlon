package com.decathlon.controller;

import com.decathlon.enums.Status;
import com.decathlon.model.Transaction;
import com.decathlon.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Api("/")
public class TransactionController extends SwaggerController{

    private final TransactionService transactionService;

    @PostMapping("/createPayment")
    @ApiOperation("create payment object in database")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions")
    @ApiOperation("get all transactions in dababase")
    public List<Transaction> findAllTransactions(){
        return transactionService.findAll();
    }

    @PutMapping("/updateStatus")
    @ApiOperation("update status for a given transaction")
    public Optional<Transaction> updateTransactionStatus(@RequestParam long id,@RequestParam Status status){
        return transactionService.modifyStatus(id, status);
    }
}
