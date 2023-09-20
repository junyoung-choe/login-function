package loginTest.loginfunction.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeopleLoginParam {
    private String email, password;
}
