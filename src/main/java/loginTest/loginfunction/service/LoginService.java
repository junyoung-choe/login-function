package loginTest.loginfunction.service;

import loginTest.loginfunction.domain.People;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean login(People p) {
//        people.setEmail(p.getEmail());
//        people.setPassword(p.getPassword());
//        System.out.println(p.getEmail() + p.getPassword());

        // 넘어온 p 객체와 db에 일치하는 ID와 비밀번호가 있다면 로그인 성공 !
        // return true;
        // 해당 p 객체를 Repository 에 넘겨주고 해당 객체가 존재하는지 확인해야한다 !



        return false;
    }

    public boolean signup(People p) {
        // 중복되는 이메일이 없다면 만들어진다 ! -> DB에 저장한다

        return false;
    }
}
