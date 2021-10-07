package study.springsecurity.dto;

import lombok.*;
import study.springsecurity.entity.Member;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String auth;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .auth(auth)
                .build();
    }
}
