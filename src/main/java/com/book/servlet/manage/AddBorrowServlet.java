package com.book.servlet.manage;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.StudentService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {
    private StudentService studentService;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        studentService=new StudentServiceImpl();
        bookService=new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        User user= (User) req.getSession().getAttribute("user");
        context.setVariable("usernameForheaderHtml",user.getUsername());//将登录的用户名显示在页面上（看header.html）
        context.setVariable("student_list",studentService.returnStudentList());//将学生信息展示到前端
        context.setVariable("activeBook_list",bookService.returnActiveBook());//将未借阅的书籍展示到前端
        ThymeleafUtil.process("addBorrow.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid= Integer.parseInt(req.getParameter("student"));//根据前端的name属性定位，获得其value属性
        int bid= Integer.parseInt(req.getParameter("book"));//根据前端的name属性定位，获得其value属性
        bookService.addBorrow(sid,bid);
        resp.sendRedirect("index");
    }
}
