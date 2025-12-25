package com.springmvc.controller;

import com.springmvc.dao.CourseDao;
import com.springmvc.entity.Course;
import com.springmvc.entity.QueryCourse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {

    public static  final  String resource="mybatis-config.xml";
    public static SqlSession session=null;


    @RequestMapping("/getCourse")
    public String getCourse( Model model) {
        CourseDao courseDao = session.getMapper(CourseDao.class);
        List<Course> courses= courseDao.getCourse();
        model.addAttribute("courses",courses);
        return "CourseView";
    }

    @RequestMapping("/queryCourse")
    public String queryCourse(@RequestParam("id")Integer id,
                              @RequestParam("courseName") String courseName,
                              @RequestParam("room") String room,
                              @RequestParam("minNumber") Integer minNumber,
                              @RequestParam("maxNumber") Integer maxNumber,
                              Model model) {
        CourseDao courseDao = session.getMapper(CourseDao.class);
        QueryCourse queryCourse= QueryCourse.builder()
                .id(id)
                .courseName(courseName)
                .room(room)
                .minNumber(minNumber)
                .maxNumber(maxNumber)
                .build();
        List<Course> courses= courseDao.queryCourse(queryCourse);
        model.addAttribute("courses",courses);
        return "CourseView";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "index";
    }
    @RequestMapping(value = "/addCourseView")
    public String addCourseView() {
        return "addCourseView";
    }

    @RequestMapping(value = "authLogin",method = RequestMethod.POST)
    public String authLogin(@RequestParam("username") String username,
                            @RequestParam("password") String  password){
        if (username.equals("admin") && password.equals("admin"))
        {
            return "redirect:/getCourse";
        }
        else
            return "index";
    }

    @RequestMapping(value = "addCourse",method = RequestMethod.POST)
    public String addCourse(@RequestParam("courseName") String courseName,
                             @RequestParam("code") String code,
                             @RequestParam("teacher") String teacher,
                             @RequestParam("room")  String room,
                             @RequestParam("number")  Integer number) {
        CourseDao courseDao = session.getMapper(CourseDao.class);
        Course course=Course.builder()
                .courseName(courseName)
                .code(code)
                .teacher(teacher)
                .room(room)
                .number(number)
                .build();
        courseDao.InsertCourse(course);
        return "redirect:/getCourse";
    }

    @RequestMapping(value = "deleteCourse")
    public String deleteCourse(@RequestParam("id") Integer id) {
        CourseDao courseDao = session.getMapper(CourseDao.class);
        courseDao.DeleteCourse(id);
        return "redirect:/getCourse";
    }
}
