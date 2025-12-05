package controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/pet",name = "PetController")
public class PetController extends HttpServlet {

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
        resp.setContentType("application/json;charset=utf-8");
        PetMapper petMapper =session.getMapper(PetMapper.class);
        List<Pet> pets = petMapper.getAllPet();
        resp.getWriter().write(JSON.toJSONString(pets));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String body = req.getReader().lines().collect(Collectors.joining());
            JSONObject json = JSON.parseObject(body);
            PetMapper petMapper = session.getMapper(PetMapper.class);
            
            String petName = json.getString("petName");
            String species = json.getString("species");
            String sex = json.getString("sex");
            String notes = json.getString("notes");
            Date time = json.getDate("time");
            String option = json.getString("option");
            
            Pet pet = Pet.builder()
                    .petName(petName)
                    .species(species)
                    .sex(sex)
                    .option(option)
                    .notes(notes)
                    .time(time)
                    .build();
            
            System.out.println("pet = " + pet);
            petMapper.addPet(pet);
            resp.getWriter().write(-1);
        } catch (Exception e) {
            resp.getWriter().write(-1);
        }
    }
}
