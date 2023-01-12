package com.book.service;

import com.book.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> returnStudentList();//获得所有学生信息
    void removeStudent(int sid);
}
