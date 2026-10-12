package com.example.bookstore.service;

import com.example.bookstore.dto.OrderRequest;
import com.example.bookstore.model.*;
import com.example.bookstore.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookstoreService {
    private final BookRepository books;
    private final OrderRepository orders;
    public BookstoreService(BookRepository books, OrderRepository orders){this.books=books;this.orders=orders;}

    public List<Book> getBooks(String q) {
        return (q == null || q.isBlank()) ? books.findAll() : books.search(q.trim());
    }

    @Transactional
    public CustomerOrder placeOrder(OrderRequest request) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(request.getCustomerName().trim());
        order.setEmail(request.getEmail().trim());
        order.setAddress(request.getAddress().trim());
        BigDecimal total = BigDecimal.ZERO;
        for (OrderRequest.Line line : request.getItems()) {
            Book book = books.findById(line.getBookId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + line.getBookId()));
            if (line.getQuantity() < 1 || line.getQuantity() > 20)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be between 1 and 20");
            if (book.getStock() < line.getQuantity())
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough stock for: " + book.getTitle());
            book.setStock(book.getStock() - line.getQuantity());
            total = total.add(book.getPrice().multiply(BigDecimal.valueOf(line.getQuantity())));
            order.getItems().add(new OrderItem(book.getId(), book.getTitle(), line.getQuantity(), book.getPrice(), order));
        }
        order.setTotal(total);
        return orders.save(order);
    }
}
