package com.book.servlet.manage;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-book")
public class addBookServlet extends HttpServlet {
    private BookService service;

    @Override
    public void init() throws ServletException {
        service=new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        User user= (User) req.getSession().getAttribute("user");
        context.setVariable("usernameForheaderHtml",user.getUsername());//将登录的用户名显示在页面上（看header.html）
        ThymeleafUtil.process("addBook.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据前端name属性找到它们的值
        int bid= Integer.parseInt(req.getParameter("bookId"));
        String title=req.getParameter("bookName");
        String descr=req.getParameter("bookDescr");
        double price= Double.parseDouble(req.getParameter("bookPrice"));
        service.addBook(bid,title,descr,price);
        resp.sendRedirect("book");
    }
}
