package com.example.librarymanagement.controller;

import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(
            @RequestBody Book book,
            @RequestParam Long categoryId) {

        return bookService.createBook(book, categoryId);
    }

    // READ ALL
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book,
            @RequestParam Long categoryId) {

        return bookService.updateBook(id, book, categoryId);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // SEARCH BY TITLE
    @GetMapping("/search/title")
    public List<Book> searchByTitle(@RequestParam String keyword) {
        return bookService.searchByTitle(keyword);
    }

    // SEARCH BY AUTHOR
    @GetMapping("/search/author")
    public List<Book> searchByAuthor(@RequestParam String keyword) {
        return bookService.searchByAuthor(keyword);
    }
}