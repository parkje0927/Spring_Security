package study.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;
import study.springsecurity.entity.User;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private String auth;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.auth = user.getAuth();
    }
}
