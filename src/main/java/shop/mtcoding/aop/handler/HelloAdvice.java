package shop.mtcoding.aop.handler;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAdvice {
    /*
     * shop.mtcoding.aop.handler.aop.Hello
     * 풀 경로가 길기 때문에 별칭을 정해주자.
     */
    @Pointcut("@annotation(shop.mtcoding.aop.handler.aop.Hello)")
    public void hello() {

    }

    @Before("hello()")
    public void helloAdvice() {
        System.out.println("안녕");
    }
}
