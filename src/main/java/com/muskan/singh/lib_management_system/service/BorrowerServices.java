package com.muskan.singh.lib_management_system.service;


import dev.apurvasingh.minilibrary.models.Borrower;
import dev.apurvasingh.minilibrary.repository.BorrowRecordRepository;
import dev.apurvasingh.minilibrary.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BorrowerServices {

    @Autowired
    private BorrowerRepository borrowerRepository ;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository ;

    //POST Add a new borrower
    public Borrower addBorrower(Borrower borrower) {
        borrower.setBorrowerId(UUID.randomUUID().toString());
        return borrowerRepository.save(borrower);
    }

    //Get borrowing history
//    public  BorrowRecord getBorrowingHistory(String uuid){
//        BorrowRecord byBorrowerRecordId = borrowRecordRepository.findByBorrowerRecordId(uuid);
//        System.out.println("Result " + byBorrowerRecordId);
//        return byBorrowerRecordId;
//    }

}
