package study.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.springsecurity.dto.UserDto;
import study.springsecurity.entity.User;
import study.springsecurity.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     *
     * 기본적인 반환 타입은 UserDetails 이다.
     * UserDetails 를 상속받은 User 로 반환 타입 지정 -> 자동으로 다운 캐스팅 됨.
     * 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
     */
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        //error custom 으로 만들기
    }

    /**
     * 회원정보 저장
     *
     * @param userDto
     * @return 회원 pk
     */
    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .auth(userDto.getAuth())
                .build()).getId();
    }
}
