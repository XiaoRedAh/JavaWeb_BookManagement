package com.book.service.impl;

import com.book.dao.StudentMapper;
import com.book.dao.UserMapper;
import com.book.entity.Book;
import com.book.entity.Student;
import com.book.entity.User;
import com.book.service.StudentService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> returnStudentList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.getAllStudent();
        }
    }

    @Override
    public void removeStudent(int sid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.deleteStudent(sid);
        }
    }
}
