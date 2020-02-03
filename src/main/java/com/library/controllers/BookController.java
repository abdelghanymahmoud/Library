package com.library.controllers;

import com.library.dto.BookDto;
import com.library.logger.LibraryLogger;
import com.library.models.Book;
import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")

public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<?> getBooks(){
        LibraryLogger.log.info("Enter method getBook in BookController class");
        LibraryLogger.log.info("Before the end of method getBook in BookController class");
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping(value = {"/{id}","/{id}/"})
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        LibraryLogger.log.info("Enter method getBookById in BookController class");
        LibraryLogger.log.info("Before the end of method getBookById in BookController class");
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto){
        LibraryLogger.log.info("Enter method addBook in BookController class");
        bookService.addBook(bookDto);
        LibraryLogger.log.info("Before the end of method addBook in BookController class");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = {"/{id}","/{id}/"})
    public ResponseEntity<?> updateBook(@PathVariable Long id,@RequestBody Book bookDto){
        LibraryLogger.log.info("Enter method updateBook in BookController class");
        LibraryLogger.log.info("Before the end of method updateBook in BookController class");
        return ResponseEntity.ok(bookService.updateBook(id,bookDto));
    }

    @DeleteMapping(value = {"/{id}","/{id}/"})
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        LibraryLogger.log.info("Enter method deleteBook in BookController class");
        bookService.deleteBook(id);
        LibraryLogger.log.info("Before the end of method deleteBook in BookController class");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
