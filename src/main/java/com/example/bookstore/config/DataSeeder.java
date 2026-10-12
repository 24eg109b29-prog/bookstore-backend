package com.example.bookstore.config;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean CommandLineRunner seedBooks(BookRepository repository) {
        return args -> {
            if(repository.count() == 0) repository.saveAll(List.of(
                new Book("Atomic Habits", "James Clear", "Self-help", new BigDecimal("399.00"), 20, "A practical guide to building good habits.", "https://covers.openlibrary.org/b/isbn/9780735211292-L.jpg"),
                new Book("The Psychology of Money", "Morgan Housel", "Finance", new BigDecimal("299.00"), 18, "Timeless lessons on wealth and behavior.", "https://covers.openlibrary.org/b/isbn/9780857197689-L.jpg"),
                new Book("Wings of Fire", "A. P. J. Abdul Kalam", "Biography", new BigDecimal("250.00"), 15, "The inspiring autobiography of India's former president.", "https://covers.openlibrary.org/b/isbn/9788173711466-L.jpg"),
                new Book("Ikigai", "Hector Garcia and Francesc Miralles", "Lifestyle", new BigDecimal("280.00"), 12, "A book about finding purpose and living well.", "https://covers.openlibrary.org/b/isbn/9780143130727-L.jpg"),
                new Book("The Alchemist", "Paulo Coelho", "Fiction", new BigDecimal("220.00"), 25, "A journey about dreams and destiny.", "https://covers.openlibrary.org/b/isbn/9780061122415-L.jpg"),
                new Book("Clean Code", "Robert C. Martin", "Technology", new BigDecimal("650.00"), 8, "A handbook of agile software craftsmanship.", "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg")
            ));
        };
    }
}
