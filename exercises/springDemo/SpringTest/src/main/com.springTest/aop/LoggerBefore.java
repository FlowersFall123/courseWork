package aop;



import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class LoggerBefore implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("调用{}的{}方法，入参：{}", target, method.getName(), Arrays.toString(args));
    }
}
