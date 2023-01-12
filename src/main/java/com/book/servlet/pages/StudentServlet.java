package com.book.servlet.pages;

import com.book.entity.User;
import com.book.service.StudentService;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private StudentService service;

    @Override
    public void init() throws ServletException {
        service=new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        User user= (User) req.getSession().getAttribute("user");
        context.setVariable("usernameForheaderHtml",user.getUsername());//将登录的用户名显示在页面上（看header.html）
        context.setVariable("student_list",service.returnStudentList());//将学生信息展示到前端
        ThymeleafUtil.process("students.html",context, resp.getWriter());
    }
}
