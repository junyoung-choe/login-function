package loginTest.loginfunction.service;

import lombok.Builder;
import lombok.Data;

// 컨트롤러에서 Param 형식으로 만들어서 보내주면 param 으로 받아서 사용하고
// 해당 값을 domain으로 넘겨준다 !
@Data
@Builder
public class PeopleSignupParam {
    private String email, password;
}
