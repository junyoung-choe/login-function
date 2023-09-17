package loginTest.loginfunction.domain;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

// db 엔티티는 무조건 설정이 있어야 한다 !
@Data
@Entity
public class People {
    // DB 접근 할때는 -> 객체에 id를 그냥 long 으로 만들었을때 0으로 들어가게 된다면 문제가 생긴다 !
    // 그래서 데이터에는 항상 래퍼런스 타입을 사용해야 한다 ! -> ok
    @Id
    private Long id;
    private String email;
    private String password;

    public People() {}

    public People(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
