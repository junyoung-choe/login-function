package loginTest.loginfunction.controller;

import lombok.Builder;
import lombok.Data;

// 클라이언트가 보낸 데이터를 controller에서 Request로 받아온다
// 그 후에 컨트롤러에서 param으로 저장해서 서비스로 던져준다
@Data
@Builder
public class PeopleSignupRequest {
    private String email, password;

}
