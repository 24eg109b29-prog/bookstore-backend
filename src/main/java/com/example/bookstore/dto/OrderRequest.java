package com.example.bookstore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class OrderRequest {
    @NotBlank private String customerName;
    @Email @NotBlank private String email;
    @NotBlank private String address;
    @NotEmpty @Valid private List<Line> items;

    public static class Line {
        @NotNull private Long bookId;
        @Min(1) @Max(20) private int quantity;
        public Long getBookId(){return bookId;} public void setBookId(Long v){bookId=v;}
        public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
    }
    public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public List<Line> getItems(){return items;} public void setItems(List<Line> v){items=v;}
}
