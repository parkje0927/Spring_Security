package study.springsecurity.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    /**
     * @GeneratedValue(자동 생성)
     *  - IDENTITY : 데이터베이스에 위임(MYSQL)
     *      + Auto_Increment
     *  - SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용(Oracle)
     *      + @SequenceGenerator 필요
     *  - TABLE : 키 생성용 테이블 사용, 모든 DB 에서 사용
     *      + @TableGenerator 필요
     *  - AUTO : 방언에 따라 자동 지정, 기본값
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    /**
     * 사용자의 권한을 컬렉션 형태로 반환
     * 단, 클래스 자료형은 GrantedAuthority 를 구현해야 함.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
