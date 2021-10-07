package study.springsecurity.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private MemberService userService;

    //test rollback 안 하도록 설정

    @Test
    @DisplayName("login 에서 넘어온 username 이 db 에 있는지 확인")
    void loadUserByUsername() {

//        UserDto userDto = new UserDto("user@test.com", "test1234", "USER");
//        userService.save(userDto);
//
//        User user = userService.loadUserByUsername("user@test.com");
//        log.debug(user.toString());

//        Assertions.assertEquals(dto.getEmail(), user2.getEmail());
    }

    @Test
    void save() {
    }
}