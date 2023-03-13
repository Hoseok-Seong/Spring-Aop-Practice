package shop.mtcoding.aop.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import shop.mtcoding.aop.handler.aop.LoginUser;
import shop.mtcoding.aop.model.User;

@Aspect
@Component
public class LoginAdvice {

    // @Pointcut("@annotation(shop.mtcoding.aop.handler.aop.LoginUser)")
    // public void loginUser() {
    // }

    @Around("execution(* shop.mtcoding.aop.controller..*.*(..))")
    public Object loginUserAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Annotation[][] annotationsPA = method.getParameterAnnotations();

        for (int i = 0; i < args.length; i++) {
            Annotation[] annotations = annotationsPA[i]; // 첫번째 파라메터의 어노테이션, 두번째 파라메터의 어노테이션
            for (Annotation anno : annotations) {
                if (anno instanceof LoginUser) {
                    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                            .getRequest();
                    HttpSession session = req.getSession();
                    User principal = (User) session.getAttribute("principal");
                    return jp.proceed(new Object[] { principal });
                }
            }
        }

        return jp.proceed();
    }

}
