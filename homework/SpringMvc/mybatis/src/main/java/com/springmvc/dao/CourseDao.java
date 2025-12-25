package com.springmvc.dao;

import com.springmvc.entity.Course;
import com.springmvc.entity.QueryCourse;

import java.util.List;

public interface CourseDao {
    List<Course> getCourse();
    Integer InsertCourse(Course course);
    Integer DeleteCourse(Integer id);
    List<Course> queryCourse(QueryCourse queryCourse);
}
