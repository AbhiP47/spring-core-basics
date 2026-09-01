package com.abhinav.transactions.service;

import com.abhinav.transactions.entity.Account;
import com.abhinav.transactions.entity.TransferRecord;
import com.abhinav.transactions.repository.AccountRepository;
import com.abhinav.transactions.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Transactional
    public void transfer(Long fromAccountId,
                         Long toAccountId,
                         BigDecimal amount) throws InterruptedException {
        Account fromAccount =
                accountRepository.findById(fromAccountId)
                        .orElseThrow(()-> new RuntimeException("User not found"));

        Account toAccount =
                accountRepository.findById(toAccountId)
                        .orElseThrow(()-> new RuntimeException("User not found"));

        fromAccount.debitAccount(amount);
        Thread.sleep(2000);
        toAccount.creditAccount(amount);

        transferRepository.save(
                new TransferRecord(
                        fromAccountId,
                        toAccountId,
                        amount,
                        LocalDate.now()
                )
        );





    }
}
