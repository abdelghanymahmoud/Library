package com.library.services;

import com.library.dto.BookDto;
import com.library.logger.LibraryLogger;
import com.library.models.Book;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
        LibraryLogger.log.info("Enter method getBook in BookRepository class");
        LibraryLogger.log.info("End of method getBook in BookRepository class");
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        LibraryLogger.log.info("Enter method getBook in BookRepository class");
        LibraryLogger.log.info("End of method getBook in BookRepository class");
        return bookRepository.findById(id).get();
    }

    public void addBook(BookDto bookDto){
        LibraryLogger.log.info("Enter method addBook in BookRepository class");
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        bookRepository.save(book);
        LibraryLogger.log.info("End of method addBook in BookRepository class");
    }

    public Book updateBook(Long id, Book bookdto){
        LibraryLogger.log.info("Enter method updateBook in BookRepository class");
        Book book = bookRepository.findById(id).get();
        if(book.getId() != bookdto.getId()){
            return null;
        }
        book.setTitle(bookdto.getTitle());
        bookRepository.save(book);
        LibraryLogger.log.info("End of method updateBook in BookRepository class");
        return book;
    }

    public void deleteBook(Long id){
        LibraryLogger.log.info("Enter method deleteBook in BookRepository class");
        bookRepository.deleteById(id);
        LibraryLogger.log.info("End of method deleteBook in BookRepository class");
    }
}
