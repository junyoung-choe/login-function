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


// if 문 사용해서 쓰고 로그인 된 화면
// post get 차이점
// @Repository 도 찾아보기 !
// @Entity

// <%@ %> -> 한글설정 import

// <%! %> -> 선언부 -> 메소드 만들거나 변수도 만들수 있다 ! -> 변수느낌 !

// <% %> -> 메소드 실행하거나 -> 변수 -> 메소드 변수  {   }

// <=% => 변수 .getName;
/*
<% ㄹㄱ%>

<% } %>
-> 깊이 파지는 말고 여기까지 이제 우리는 다룰줄 알잖아 타임 리프를 ->
-> 아 이래서 스프링을 쓰는구나 왜 JSP 이게 안쓰는구나 !

Get-> html 어떠한 사용자의 입력을 가져올라고 쓰는거잖아
우리 개발자가 을때는 getParameter -> 스프링에선 @MedelAttribute 객체로 받아오고

        request.setAttri -> 어떠한 값을 html 뿌려주려고 쓰는거야
이게ㅔ 스프링에서는 Model model !
model.setAttribute

-=>

서블렛 JSP 가 왜 등장했냐 !
자바 스크립트와 CSS, html 이거는 정적인 언어야 !
그래서 서버와 통신이 안되고 이것은 즉 페이지 마다 어떠한 값을 유지시킬수 없다 !
그러면 이 값을 유지 시키는게 왜 필요할가>?
로그인이 된 사용자를 구별하고 그리고 해당 사용자에게 어떠한 추가적인 이벤트를 주기 위해서 -> 쓰는거다 !

ssr csr -> 말이 많다 !


*/
