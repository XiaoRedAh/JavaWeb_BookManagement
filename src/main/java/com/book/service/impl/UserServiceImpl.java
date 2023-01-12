package com.book.service.impl;

import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isLogin(String username, String password, HttpSession session) {//判断是否登录成功
        try(SqlSession sqlSession= MybatisUtil.getSession(true)){
            UserMapper mapper=sqlSession.getMapper(UserMapper.class);
            User user=mapper.getUser(username,password);
            if(user==null)return false;//登录失败返回false
            else{
                //登录成功，在HttpSession记录下来，并返回true
                session.setAttribute("user",user);
                return true;
            }
        }
    }
}
