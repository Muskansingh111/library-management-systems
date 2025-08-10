package com.muskan.singh.lib_management_system.controller;

import dev.apurvasingh.minilibrary.models.BorrowRecord;
import dev.apurvasingh.minilibrary.services.BorrowRecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/BorrowRecords")
@RequestMapping
public class BorrowRecordController {

    @Autowired
    private BorrowRecordServices borrowRecordServices ;


    //Borrow a book (POST)
    @PostMapping("/BorrowBook")
    public ResponseEntity<BorrowRecord> borrowBook(@RequestParam String borrowerId,
                                                   @RequestParam String bookId) {
        BorrowRecord record = borrowRecordServices.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok(record);
    }

    //Return a borrowed book
    @PostMapping("/return")
    public ResponseEntity<BorrowRecord> returnBook(@RequestParam String borrowerId,@RequestParam String bookId) {
        BorrowRecord record = borrowRecordServices.returnBook(borrowerId , bookId);
        return ResponseEntity.ok(record);
    }




}
