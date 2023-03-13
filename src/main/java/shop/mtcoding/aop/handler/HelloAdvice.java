package shop.mtcoding.aop.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    // @Before("hello()")
    // public void helloAdvice() {
    // System.out.println("안녕");
    // }

    // @After("getMapping() || hello()")
    // public void getAdvice() {
    // System.out.println("getMapping 실행됨");
    // }

    @Around("hello()")
    public Object helloAdvice(ProceedingJoinPoint jp) throws Throwable { // ProceedingJoinPoint는 메서드 정보를 갖고 있다
        Object[] args = jp.getArgs();
        System.out.println("Parameter size: " + args.length);

        for (Object arg : args) {
            if (arg instanceof String) {
                String username = (String) arg;
                System.out.println(username + "님 안녕하세요");
            }
        }
        return jp.proceed();
    }
}
