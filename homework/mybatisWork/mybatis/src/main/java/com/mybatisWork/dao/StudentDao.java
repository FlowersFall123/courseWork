package com.mybatisWork.dao;

import com.mybatisWork.entity.Student;
import com.mybatisWork.entity.StudentAndHobby;


import java.util.List;


public interface StudentDao {
    List<StudentAndHobby> getStudentAndHobby();
    Integer InsertStudent(Student student);
    Integer DeleteStudent(Long id);
    Long getIdByHobbyName(String hobbyName);
}
