package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private String title;
    private int quantity;
    private BigDecimal unitPrice;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id")
    @JsonIgnore
    private CustomerOrder order;

    public OrderItem() {}
    public OrderItem(Long bookId, String title, int quantity, BigDecimal unitPrice, CustomerOrder order) {
        this.bookId=bookId; this.title=title; this.quantity=quantity; this.unitPrice=unitPrice; this.order=order;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getBookId(){return bookId;} public void setBookId(Long v){bookId=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
    public BigDecimal getUnitPrice(){return unitPrice;} public void setUnitPrice(BigDecimal v){unitPrice=v;}
    public CustomerOrder getOrder(){return order;} public void setOrder(CustomerOrder v){order=v;}
}
