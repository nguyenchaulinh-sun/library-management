package com.example.librarymanagement;

import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Category;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LibraryManagementApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCategoryAndBookRelationship() {

        // CREATE CATEGORY
        Category category = new Category(
                "Programming Test",
                "Category created for testing"
        );

        Category savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory.getId());

        // CREATE BOOK
        Book book = new Book(
                "Spring Boot Test Book",
                "Test Author",
                "9780000000001",
                5,
                savedCategory
        );

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());

        // READ AND CHECK RELATIONSHIP
        Book foundBook = bookRepository
                .findById(savedBook.getId())
                .orElseThrow();

        assertNotNull(foundBook.getCategory());

        assertEquals(
                savedCategory.getId(),
                foundBook.getCategory().getId()
        );

        // UPDATE
        foundBook.setQuantity(10);
        Book updatedBook = bookRepository.save(foundBook);

        assertEquals(10, updatedBook.getQuantity());

        // DELETE BOOK
        bookRepository.delete(updatedBook);

        assertFalse(
                bookRepository.existsById(updatedBook.getId())
        );

        // DELETE CATEGORY
        categoryRepository.delete(savedCategory);

        assertFalse(
                categoryRepository.existsById(savedCategory.getId())
        );
    }
}