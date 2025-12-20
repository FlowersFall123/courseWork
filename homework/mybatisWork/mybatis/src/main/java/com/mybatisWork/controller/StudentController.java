package com.mybatisWork.controller;

import com.mybatisWork.dao.StudentDao;
import com.mybatisWork.entity.Student;
import com.mybatisWork.entity.StudentAndHobby;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    public static  final  String resource="mybatis-config.xml";
    public static SqlSession session=null;


    @RequestMapping("/getAllStudents")
    public String getAllStudents( Model model) {
        StudentDao studentDao = session.getMapper(StudentDao.class);
        List<StudentAndHobby> studentAndHobbies=studentDao.getStudentAndHobby();
        model.addAttribute("studentAndHobbies",studentAndHobbies);
        return "Success";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "index";
    }

    @RequestMapping(value = "authLogin",method = RequestMethod.POST)
    public String authLogin(@RequestParam("username") String username,
                            @RequestParam("password") String  password){
        if (username.equals("admin") && password.equals("admin"))
        {
            return "redirect:/getAllStudents";
        }
        else
            return "index";
    }

    @RequestMapping(value = "addStudent",method = RequestMethod.POST)
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("hobbyName") String hobbyName,
                             @RequestParam("sex") String sex,
                             @RequestParam("birthday")  @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")  java.util.Date birthday) {
        StudentDao studentDao = session.getMapper(StudentDao.class);
        Long id = studentDao.getIdByHobbyName(hobbyName);
        Student student = Student.builder()
                .name(name)
                .sex(sex)
                .birthday(birthday)
                .hobby(id)
                .build();
        studentDao.InsertStudent(student);
        return "redirect:/getAllStudents";
    }

    @RequestMapping(value = "deleteStudent")
    public String deleteStudent(@RequestParam("id") Long id) {
        StudentDao studentDao = session.getMapper(StudentDao.class);
        studentDao.DeleteStudent(id);
        return "redirect:/getAllStudents";
    }
}
