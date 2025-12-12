package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Aspect
@Component
public class AroundAop {
    @Pointcut("execution(* service.impl.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName =joinPoint.getSignature().getName();
        System.out.println("methodName = " + methodName);
        Object[] args = joinPoint.getArgs();
        System.out.println("args = " + args);
        System.out.println("===============修改参数===============");
        for (Object arg : args) {
            Field usernameField = ReflectionUtils.findField(arg.getClass(), "username");
            if (usernameField != null){
                usernameField.setAccessible(true);
                usernameField.set(arg, "AroundAop");
            }
        }
        System.out.println("===============执行目标方法===============");
        Object result = joinPoint.proceed(args);
        System.out.println("===============目标方法执行完毕===============");
        System.out.println("result = " + result);

        return null;
    }
}
