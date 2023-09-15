package loginTest.loginfunction.controller;

import loginTest.loginfunction.domain.People;
import loginTest.loginfunction.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.util.Arrays;

/*
여기서 모든
 */
@Controller
public class loginController {
    LoginService loginService;
    public loginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // @GetMapping("/") -> / 는 url 이다
    // return "homeForm"; homeForm -> html 파일이다
    @GetMapping("/")
    public String home() {
        return "homeForm";
    }

    @GetMapping("signup")
    public String signup() {
        return "signupForm";
    }

    @GetMapping("login")
    public String login() {

        return "loginForm";


    }

    /*
    @ModelAttribute 는 loginForm 에서 넘어오는 값들을 모두 하나의 객체로 만들어서
    People의 가장 적게 사용하는 파라미터를 이용한 생성자를 사용해서 객체로 등록이 된다
     */
    @PostMapping("login")
    public String login(@ModelAttribute People people) {
        loginService.login(people);
        return "loginForm";
    }

}
