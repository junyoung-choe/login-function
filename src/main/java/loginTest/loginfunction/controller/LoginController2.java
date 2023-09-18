package loginTest.loginfunction.controller;

import loginTest.loginfunction.domain.People;
import loginTest.loginfunction.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
여기서 모든
 */
@Controller
public class LoginController2 {
    LoginService loginService;

    public LoginController2(LoginService loginService) {
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



    /**
     * 로그인 V 2
     */
    @PostMapping("login")
    public String login(@ModelAttribute People people, HttpServletRequest request, Model model) { // Model을 이용해서 html 파일에 객체를 넘겨준다 !

        if (!loginService.login(people)) {
            return "loginForm";

        }
        // 세션 등록
        HttpSession session = request.getSession();
        session.setAttribute("people",people); // 객체 자체를 세션으로 등록
        session.setMaxInactiveInterval(60 * 30);   // 디폴트 30분 !

        model.addAttribute("people",people);
        boolean condition = true;
        model.addAttribute("condition",condition);

        return "homeForm";
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute People people, Model model) {

        if (!loginService.signup(people)) {
            return "signupForm";
        }
        boolean condition = false;
        model.addAttribute("condition", condition);

        return "homeForm";
    }


    @GetMapping("logout")
    public String logout(HttpSession session) { // 세션을 가져온다
        session.invalidate(); // 해당 세션을 종료한다
        return "homeForm";
    }
}