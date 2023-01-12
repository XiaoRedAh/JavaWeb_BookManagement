package com.book.servlet.manage;

import com.book.service.StudentService;
import com.book.service.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-student")
public class deleteStudent extends HttpServlet {
    private StudentService service;

    @Override
    public void init() throws ServletException {
        service=new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid=Integer.parseInt(req.getParameter("sid"));
        service.removeStudent(sid);
        resp.sendRedirect("student");
    }
}
