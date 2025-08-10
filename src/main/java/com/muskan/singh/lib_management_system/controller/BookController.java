package com.muskan.singh.lib_management_system.controller;

import dev.apurvasingh.minilibrary.models.Book;
import dev.apurvasingh.minilibrary.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Books")
public class BookController {

    @Autowired
    private BookServices bookServices ;

    //Add a new book
    @PostMapping("addBooks")
    public Book addBooks(@RequestBody Book book){
        return this.bookServices.addBooks(book);
    }

    //Get all books
    @GetMapping("getAllBooks")
    public List<Book> getAllBooks(){
        return bookServices.getAllBooks();
    }


    //Get only available books
    @GetMapping("getBooksByAvailability")
    public List<Book> getBooksByAvailability(@RequestParam(required = false) Boolean available) {
        if (available != null) {
            return bookServices.getBooksByAvailability(available);
        }
        return bookServices.getAllBooks(); // fallback if no filter
    }


}
