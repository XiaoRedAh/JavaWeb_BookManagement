package com.book.entity;

import lombok.Data;

@Data
public class Book {
    private int bid;
    private String bookName;
    private String descr;
    private double price;
    private int isBorrowed;//0没借出，1已经借出
}
