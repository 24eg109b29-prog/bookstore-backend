package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookstoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookstoreService service;
    private final BookRepository repository;
    public BookController(BookstoreService service, BookRepository repository){this.service=service;this.repository=repository;}

    @GetMapping public List<Book> all(@RequestParam(required=false) String q){return service.getBooks(q);}
    @GetMapping("/{id}") public Book one(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found"));}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Book create(@Valid @RequestBody Book book){book.setId(null);return repository.save(book);}
    @PutMapping("/{id}") public Book update(@PathVariable Long id,@Valid @RequestBody Book book){
        Book old=repository.findById(id).orElseThrow(()->new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found"));
        old.setTitle(book.getTitle());old.setAuthor(book.getAuthor());old.setCategory(book.getCategory());old.setPrice(book.getPrice());old.setStock(book.getStock());old.setDescription(book.getDescription());old.setCoverUrl(book.getCoverUrl());
        return repository.save(old);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){
        if(!repository.existsById(id)) throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found");
        repository.deleteById(id);
    }
}
