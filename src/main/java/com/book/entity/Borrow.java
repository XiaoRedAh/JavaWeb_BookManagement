package com.book.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Borrow {
    private int id;
    private int bookId;
    private String bookName;
    private Date borrowTime;
    private String borrowStudent;
    private String studentId;
}
