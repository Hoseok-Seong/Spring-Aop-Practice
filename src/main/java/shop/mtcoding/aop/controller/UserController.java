package shop.mtcoding.aop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.aop.handler.aop.LoginUser;
import shop.mtcoding.aop.model.User;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final HttpSession session;

    @GetMapping("/login")
    public String login() {
        User user = new User(1, "ssar", "1234", "01011112222");
        session.setAttribute("principal", user);
        return "로그인 성공";
    }

    @GetMapping("/user/1")
    public String userInfo() { // 인증 필요 없음
        return "인증 필요없는 접근";
    }

    @GetMapping("/auth/1")
    public String authInfo(@LoginUser User user) { // 인증 필요함
        System.out.println("자동으로 값 주입됨");
        System.out.println(user.getUsername());
        return "인증 성공";
    }
}
