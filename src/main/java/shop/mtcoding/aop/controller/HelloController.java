package shop.mtcoding.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/v1")
    public String v1() {
        return "v1";
    }

    @GetMapping("/v2")
    public String v2() {
        return "v2";
    }
}
