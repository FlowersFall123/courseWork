package controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import entity.Login;
import entity.Pet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.PetMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/login",name = "LoginController")
public class LoginController extends HttpServlet {

    private static  final  String resource="mybatis-config.xml";
    private static SqlSession session=null;

    @Override
    public void init() throws ServletException {
        Reader reader= null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        session=sessionFactory.openSession(true);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PetMapper petMapper =session.getMapper(PetMapper.class);
        String body = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = JSON.parseObject(body);

        String username = json.getString("username");
//        System.out.println("username = " + username);
        String password = json.getString("password");
//        System.out.println("password = " + password);

        Pet pet = petMapper.LoginAccount(new Login(username,password));
//        System.out.println("user = " + user);
        if(pet !=null){
            req.getSession().setAttribute("user", pet);
            resp.getWriter().write(JSON.toJSONString(pet));
        }else{
            resp.getWriter().write("{\"code\": -1}");
        }
    }
}
