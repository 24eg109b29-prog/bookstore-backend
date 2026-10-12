package com.example.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String title;
    @NotBlank private String author;
    @NotBlank private String category;
    @NotNull @DecimalMin("0.0") private BigDecimal price;
    @Min(0) private int stock;
    private String description;
    private String coverUrl;

    public Book() {}
    public Book(String title, String author, String category, BigDecimal price, int stock, String description, String coverUrl) {
        this.title=title; this.author=author; this.category=category; this.price=price;
        this.stock=stock; this.description=description; this.coverUrl=coverUrl;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getAuthor(){return author;} public void setAuthor(String author){this.author=author;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal price){this.price=price;}
    public int getStock(){return stock;} public void setStock(int stock){this.stock=stock;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getCoverUrl(){return coverUrl;} public void setCoverUrl(String coverUrl){this.coverUrl=coverUrl;}
}
