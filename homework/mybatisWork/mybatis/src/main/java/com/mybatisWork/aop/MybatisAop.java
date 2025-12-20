package com.mybatisWork.aop;

import com.mybatisWork.controller.StudentController;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;

@Aspect
@Component
public class MybatisAop {

    @Pointcut("execution(* com.mybatisWork.controller.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Reader reader = Resources.getResourceAsReader(StudentController.resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 开启 session
        StudentController.session = sessionFactory.openSession(true);

        try {
            result = joinPoint.proceed();
        } finally {
            // 无论成功还是异常，必须关闭 session，否则连接池会耗尽
            if (StudentController.session != null) {
                StudentController.session.close();
            }
        }

        return result;
    }
}
