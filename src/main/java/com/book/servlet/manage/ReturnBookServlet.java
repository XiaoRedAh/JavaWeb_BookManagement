package com.book.servlet.manage;

import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/return-book")
public class ReturnBookServlet extends HttpServlet {
    private BookService service;

    @Override
    public void init() throws ServletException {
        service= new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Context context=new Context();
        int id= Integer.parseInt(req.getParameter("id"));
        int bid= Integer.parseInt(req.getParameter("bid"));
        service.returnBook(id,bid);
        resp.sendRedirect("index");
    }
}
