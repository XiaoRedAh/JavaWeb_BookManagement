package com.book.servlet.auth;


import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service;

    @Override
    public void init() throws ServletException {
        service=new UserServiceImpl();//初始化时创建好
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        if(req.getSession().getAttribute("loginFailure")!=null){//在doPost()中判断出登录失败，打出红色提示(前端用Thymeleaf模板改)
            context.setVariable("failure",true);
            context.removeVariable("failure");//删除该标记，使得页面刷新后，红色提示消失
        }
        if(req.getSession().getAttribute("user")!=null){//若已经登录过，就无需再登录
            resp.sendRedirect("index");
        }
        ThymeleafUtil.process("login.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("usernameFromHtml");
        String password=req.getParameter("passwordFromHtml");
        String remember=req.getParameter("remember");
        if(service.isLogin(username,password, req.getSession())){//登录成功,跳转到index页面
            resp.sendRedirect("index");
        }else{//登录失败
            req.getSession().setAttribute("loginFailure",new Object());
            this.doGet(req,resp);
        }
    }
}
