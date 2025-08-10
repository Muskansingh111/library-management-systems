package com.muskan.singh.lib_management_system.service;

import dev.apurvasingh.minilibrary.models.Book;
import dev.apurvasingh.minilibrary.models.BorrowRecord;
import dev.apurvasingh.minilibrary.models.Borrower;
import dev.apurvasingh.minilibrary.repository.BookRepository;
import dev.apurvasingh.minilibrary.repository.BorrowRecordRepository;
import dev.apurvasingh.minilibrary.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowRecordServices {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository ;

    @Autowired
    private BorrowerRepository borrowerRepository ;

    @Autowired
    private BookRepository bookRepository;


    //Borrow a book (POST)
    public BorrowRecord borrowBook(String borrowerId , String bookId){
        Borrower borrower = borrowerRepository.findById((borrowerId))
        .orElseThrow(()-> new RuntimeException("Borrower not found"));

        Book book = bookRepository.findById(bookId)
        .orElseThrow(()-> new RuntimeException("Book not found")) ;

        if (!book.isAvailable()){
            throw new RuntimeException("Book is already borrowed") ;
        }
        // Create BorrowRecord
        BorrowRecord record = new BorrowRecord();
        record.setBorrowerRecordId(UUID.randomUUID().toString());
        record.setBorrower(borrower);
        record.setBorrowedDate(LocalDateTime.now());
        record.setReturnedDate(null);
        record.setBook(book);

        System.out.println();
        BorrowRecord save = borrowRecordRepository.save(record);
        // Mark book as unavailable
        book.setAvailable(false);
        // Save updated book and borrow record
        bookRepository.save(book) ;
        return save ;

    }

    //Return a book (POST)
    public BorrowRecord returnBook(String borrowerId , String bookId) {

        BorrowRecord record1 = borrowRecordRepository.findAll().stream()
                .filter(borrowRecord -> borrowRecord.getBorrower().getBorrowerId().equals(borrowerId)
                && borrowRecord.getBook().getBookId().equals(bookId)
                        && borrowRecord.getReturnedDate()==null).findAny().get();


        Optional<Borrower> byId = this.borrowerRepository.findById(borrowerId);
        Optional<Book> byId1 = this.bookRepository.findById(bookId);


        BorrowRecord record = borrowRecordRepository.findByBorrowerAndBookAndReturnedDateIsNull(byId.get(),byId1.get());

        if(record == null){
              throw  new RuntimeException("Borrow record not found");
        }

        if (record.getReturnedDate() != null) {
            throw new RuntimeException("Book is already returned");
        }
        // Create ReturnRecord
        record.setReturnedDate(LocalDateTime.now());
        Book book = record.getBook();
        book.setAvailable(true);
        bookRepository.save(book) ;
        return borrowRecordRepository.save(record) ;

    }

}
