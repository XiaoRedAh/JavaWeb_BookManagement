package com.book.dao;

import com.book.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    @Select("select * from student")
    List<Student> getAllStudent();
    @Delete("delete from student where sid=${sid}")
    void deleteStudent(int sid);
}
