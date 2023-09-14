package loginTest.loginfunction.controller;

import loginTest.loginfunction.domain.People;
import loginTest.loginfunction.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("login")
    public String login(People people) {
        loginService.login(people);
        return "loginForm";
    }

}
