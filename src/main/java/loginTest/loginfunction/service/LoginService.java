package loginTest.loginfunction.service;

import loginTest.loginfunction.domain.People;
import loginTest.loginfunction.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceProperty;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    PeopleRepository peopleRepository;
    public boolean login(People p) {
//        people.setEmail(p.getEmail());
//        people.setPassword(p.getPassword());
//        System.out.println(p.getEmail() + p.getPassword());

        // 넘어온 p 객체와 db에 일치하는 ID와 비밀번호가 있다면 로그인 성공 !
        // return true;
        // 해당 p 객체를 Repository 에 넘겨주고 해당 객체가 존재하는지 확인해야한다 !

        List<People> list = peopleRepository.findAll();
        for(People people : list) {
            if (people.getEmail().equals(p.getEmail()) && people.getPassword().equals(p.getPassword()))
                return true;
        }
        peopleRepository.save(p);

        // 중복 확인을 위해서 @Query 도 사용한다 !
        // Optional<People> 널 값을 방지한다 !
        // peopleRepository.findById(p.getId()); // 이 전에 컨트롤러에서 이미 Id 는 부여가 된다 !

        return false;
    }

    public boolean signup(People p) {
        // 중복되는 이메일이 없다면 만들어진다 ! -> DB에 저장한다
        //System.out.println("p = " + p);

        List<People> list = peopleRepository.findAll();
        for(People people : list) {
            if (people.getEmail().equals(p.getEmail()))
                return false;
        }
        peopleRepository.save(p);

        // 해당 회원 가입이 됬는지 아닌지 따져보지만
        // 이 로직에서는 중복되는 아이디가 있다면 비밀번호만 바꿔준다 !

        return true;
    }
}
