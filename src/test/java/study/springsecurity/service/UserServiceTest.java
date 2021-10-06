package study.springsecurity.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.springsecurity.dto.UserDto;
import study.springsecurity.entity.User;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    //test rollback 안 하도록 설정

    @Test
    @DisplayName("login 에서 넘어온 username, password 검증")
    void loadUserByUsername() {

//        User user1 = new User("user@test.com", "test1234", "USER");
//        UserDto dto = new UserDto(user1);
//        userService.save(dto);

        User user2 = userService.loadUserByUsername("user@test.com");
        log.debug(user2.toString());

//        Assertions.assertEquals(dto.getEmail(), user2.getEmail());
    }

    @Test
    void save() {
    }
}