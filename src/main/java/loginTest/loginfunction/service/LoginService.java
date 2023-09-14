package loginTest.loginfunction.service;

import loginTest.loginfunction.domain.People;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    People people = new People();

    public People login(People p) {

        people.setEmail(p.getEmail());
        people.setPassword(p.getPassword());

        System.out.println(p.getEmail() + p.getPassword());
        return people;
    }
}
