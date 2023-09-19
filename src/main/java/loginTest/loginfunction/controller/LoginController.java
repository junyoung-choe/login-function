package loginTest.loginfunction.controller;

import loginTest.loginfunction.domain.People;
import loginTest.loginfunction.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/*
여기서 모든
 */
public class LoginController {
    LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // @GetMapping("/") -> / 는 url 이다
    // return "homeForm"; homeForm -> html 파일이다
    @GetMapping("/")
    public String home() {
        return "homeForm";
    }

    // 홈 화면에서 회원 가입 버튼을 클릭하면 매핑된다
    @GetMapping("signup")
    public String signup() {
        return "signupForm";
    }

    // 홈 화면에서 로그인 버튼을 클릭하면 매핑된다
    @GetMapping("login")
    public String login() {
        return "loginForm";
    }

    /*
    @ModelAttribute 는 loginForm 에서 넘어오는 값들을 모두 하나의 객체로 만들어서
    People의 가장 적게 사용하는 파라미터를 이용한 생성자를 사용해서 객체로 등록이 된다

     HttpServletResponse response -> 브라우저에 어떠한 결과를 넘겨주기 위해서 사용한다
     */

    /**
     * 로그인 V 1
     */
    @PostMapping("login")
    public String login(@ModelAttribute People people, HttpServletResponse response, Model model) { // Model을 이용해서 html 파일에 객체를 넘겨준다 !

        // DB 연결후 사용해야 한다 !
        if (!loginService.login(people)) { // 로그인 구현 메소드로 이동한다 !
            return "loginForm"; //  해당 email이 없다면 다시 로그인 페이지로 이동한다
            // 아이디 비밀번호를 다시 입력하시오
        }

        // 로그인에 성공한다면 ! -> Model 에 해당 값을 담아준다 !
        // 여기서 앞의 "" 이름이 뷰 파일에서 그대로 사용된다 !
        model.addAttribute("people",people);

        boolean condition = true;
        model.addAttribute("condition",condition);

        // 로그인 구현은 가능하나 로그아웃의 기능을 구현할때 DB에 해당 멤버를 지워버릴수 없다 !
        // 따라서 뭔가 로그인 했다는 표시와 그 표시를 지우는 과정으로  로그인을 구현해야 한다 !
        // -> 쿠키를 사용한 로그인 처리 ( 위에서 해당 사람이 로그인이 가능하다면 여기로 내려온다 )

        // html 파일에서 넘어온 이메일의 이름으로 세션을 걸어준다 !
        Cookie cookie = new Cookie("email", people.getEmail());

        // 쿠키 시간을 디폴트로 설정한다.
        //cookie.setMaxAge();
        // 서버에 해당 유저의 쿠키 정보를 넘겨준다
        response.addCookie(cookie);

        return "homeForm";
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute People people, Model model) {
        //DB 연결후 -> 해당 이메일과 같은 이메일이 없다면 성공해서 true 를 리턴 받는다
        //System.out.println(people);

        if(!loginService.signup(people)) {
            return "signupForm";
        }
        // condition 이라는 변수를 넘겨줘야한다 !
        boolean condition = false;
        model.addAttribute("condition",condition);

        return "homeForm";
    }


//    @PostMapping("loginedHome")
//    public String logined(@ModelAttribute People people) {
//        System.out.println();
//        return "loginedHomeForm";
//    }


    @GetMapping("logout")
    public String logout(HttpServletResponse response) {
        // 쿠키 시간을 만료 시킨다 !
        Cookie cookie = new Cookie("email", null);
        // 음수로 값을 준다면 브라우저를 닫는 순간 부여받은 쿠키를 지우도록 하는것이다
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "homeForm";
    }

    /*
    @GetMapping("")

    Cookie cookie = new Cookie("emain", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);

     */

    // 로그아웃 화면을 구성하기 위한 로그인후 home 화면이 필요하다 !

}



