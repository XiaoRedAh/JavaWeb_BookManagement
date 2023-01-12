package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> returnBorrowList() {//获得所有的借阅数据
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
            return mapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(int id,int bid) {//还书
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
            mapper.deleteBorrow(id);
            mapper.updateBookCanBorrow(bid);
        }
    }

    @Override
    public List<Book> returnBookList() {//获得所有书籍信息
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
            return mapper.getAllBook();
        }
    }

    @Override
    public List<Book> returnActiveBook() {
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
            return mapper.getBookCanBorrow();
        }
    }

    @Override
    public void removeBook(int bid) {
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
            mapper.insertBorrow(bid,sid);
            mapper.updateBookStatus(bid);
        }
    }

    @Override
    public void addBook(int bid, String title, String descr, double price) {
        try(SqlSession sqlsession= MybatisUtil.getSession(true)){
            BookMapper mapper=sqlsession.getMapper(BookMapper.class);
           mapper.insertBook(bid, title,descr,price);
        }
    }
}
