package com.muskan.singh.lib_management_system.service;

import dev.apurvasingh.minilibrary.models.Book;
import dev.apurvasingh.minilibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository ;

    //Add a new book (POST)
    public Book addBooks(Book book) {
        book.setBookId(UUID.randomUUID().toString());
        return (Book)this.bookRepository.save(book);
    }

    //Get all books (GET)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Get only available books(GET)
    public List<Book> getBooksByAvailability(boolean available){
        return bookRepository.findByAvailable(available);
    }
}
