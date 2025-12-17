package controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import entity.Login;
import entity.Pet;

import mapper.PetMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Collectors;

//@WebServlet(urlPatterns = "/login",name = "LoginController")
@Controller
public class LoginController extends HttpServlet {

    private static  final  String resource="mybatis-config.xml";
    private static SqlSession session=null;


//    @Override
//    public void init() throws ServletException {
//        Reader reader= null;
//        try {
//            reader = Resources.getResourceAsReader(resource);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
//        session=sessionFactory.openSession(true);
//        super.init();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PetMapper petMapper =session.getMapper(PetMapper.class);
//        String body = req.getReader().lines().collect(Collectors.joining());
//        JSONObject json = JSON.parseObject(body);
//
//        String username = json.getString("username");
////        System.out.println("username = " + username);
//        String password = json.getString("password");
////        System.out.println("password = " + password);
//
//        Pet pet = petMapper.LoginAccount(new Login(username,password));
////        System.out.println("user = " + user);
//        if(pet !=null){
//            req.getSession().setAttribute("user", pet);
//            resp.getWriter().write(JSON.toJSONString(pet));
//        }else{
//            resp.getWriter().write("{\"code\": -1}");
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody Login login){
        Reader reader= null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);


        session=sessionFactory.openSession(true);
        PetMapper petMapper =session.getMapper(PetMapper.class);
        System.out.println("login = " + login);
        Pet pet = petMapper.LoginAccount(login);
        if(pet !=null){
            return "{\"code\": 1}";
        }else{
            return "{\"code\": -1}";
        }
    }
}
