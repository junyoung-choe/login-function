package loginTest.loginfunction.controller;

import lombok.Builder;
import lombok.Data;

// 클라이언트가 html 폼에 넣는 데이터를 받아서 저장한다
@Data
@Builder
public class PeopleLoginRequest {
    private String email, password;


}
