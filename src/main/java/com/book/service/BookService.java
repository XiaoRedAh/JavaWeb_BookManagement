package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;

import java.util.List;

public interface BookService {
    List<Borrow>  returnBorrowList();//获得借阅关系列表
    void returnBook(int id,int bid);//还书
    List<Book> returnBookList();//获得所有书籍的信息
    List<Book> returnActiveBook();//获得还没被借阅的书籍
    void removeBook(int bid);
    void addBorrow(int sid,int bid);
    void addBook(int bid,String title,String descr,double price);
}
