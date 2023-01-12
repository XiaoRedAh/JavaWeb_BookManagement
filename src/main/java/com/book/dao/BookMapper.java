package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface BookMapper {//将数据表里的column数据映射到对应Borrow实体类里(property)
    @Results({
            @Result(column="id",property = "id"),
            @Result(column="bid",property = "bookId"),
            @Result(column="title",property = "bookName"),
            @Result(column="borrowTime",property = "borrowTime"),
            @Result(column="name",property = "borrowStudent"),
            @Result(column="sid",property = "studentId")
    })
    @Select("select * from student,book,borrow where book.bid=borrow.bid and student.sid=borrow.sid")
    List<Borrow> getBorrowList();//获得借阅关系表
    @Delete("delete from borrow where id=#{id}")
    void deleteBorrow(int id);//还书后，删除借阅关系
    @Update("update book set isBorrowed=0 where bid=#{bid}")
    void updateBookCanBorrow(int bid);//还书后，将书籍设为可借阅
    @Delete("delete from book where bid=#{bid}")
    void deleteBook(int bid);//删书
    @Results({
            @Result(column="bid",property = "bid"),
            @Result(column="title",property = "bookName"),
            @Result(column="descr",property = "descr"),
            @Result(column="price",property = "price"),
            @Result(column="isBorrowed",property = "isBorrowed"),
    })//因为数据表里的title和实体类对应的变量bookName名称不同，所以不用Results映射不到
    @Select("select * from book")
    List<Book> getAllBook();
    @Results({
            @Result(column="bid",property = "bid"),
            @Result(column="title",property = "bookName"),
            @Result(column="descr",property = "descr"),
            @Result(column="price",property = "price"),
            @Result(column="isBorrowed",property = "isBorrowed"),
    })//因为数据表里的title和实体类对应的变量bookName名称不同，所以不用Results映射不到
    @Select("select * from book where isBorrowed=0")
    List<Book> getBookCanBorrow();//获得可以借阅的书
    @Update("update book set isBorrowed=1 where bid=#{bid}")
    void updateBookStatus(int bid);//借书后，将书籍设为不可借阅
    @Insert("insert into borrow(bid,sid,borrowTime) values(#{bid},#{sid},NOW())")
    void insertBorrow(@Param("bid") int bid,@Param("sid")int sid);//借书后，添加借阅信息
    @Insert("insert into book(bid,title,descr,price) values(#{bid},#{title},#{descr},#{price})")
    void insertBook(@Param("bid") int bid,@Param(("title")) String title,@Param("descr") String descr,@Param("price") double price);//添加书籍

}
