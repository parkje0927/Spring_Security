package study.springsecurity.securingweb;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import study.springsecurity.service.MemberService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @EnableWebSecurity
     * - Spring Security 를 활성화한다는 의미의 어노테이션
     *
     * extends WebSecurityConfigureAdapter
     * - WebSecurityConfigureAdapter 는 Spring Security 의 설정 파일로서의 역할을 하기 위해 상속해야 하는 클래스
     */

    private final MemberService memberService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * public void configure(WebSecurity web)
     * - 인증을 무시할 경로들을 설정해놓을 수 있다.
     * WebSecurity
     * - 보안예외처리(정적리소스, HTML)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**", "/css/**", "/js/**");
    }

    /**
     * public void configure(HttpSecurity http)
     * - http 관련 인증 설정이 가능하다.
     * HttpSecurity
     * - 보안 처리
     */
    /**
     * anyMatchers : 경로 설정과 권한 설정이 가능
     *  permitAll() : 누구나 접근이 가능
     *  hasRole() : 특정 권한이 있는 사람만 접근 가능
     *  authenticated() : 권한이 있으면 무조건 접근 가능
     *  anyRequest() : anyMatchers 에서 설정하지 않은 나머지 경로를 의미
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //접근에 대한 인증 설정이 가능
                .antMatchers("/**").permitAll() //누구나 접근 허용
                .antMatchers("/member/myInfo").hasRole("MEMBER") //MEMBER, ADMIN 만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") //ADMIN 만 접근 가능
                .anyRequest().authenticated() //나머지 요청들은 권한의 종류에 강관 없이 권한이 있어야 접근 가능
                .and()

                .formLogin()
                .loginPage("/member/login") //로그인 페이지 링크
                .defaultSuccessUrl("/member/login/result") //로그인 성공 후 리다이렉트 주소
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/logout/result") //로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) //세션 날리기
                .and()

                .exceptionHandling().accessDeniedPage("/member/denied")
                .and()

                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and()

                .headers()
                .frameOptions()
                .disable()
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //해당 서비스(userService) 에서는 UserDetailsService 를 implements 해서 loadUserByUserName() 구현해야 함.
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    RestAuthenticationFailureHandler authenticationFailureHandler() {
//        return new RestAuthenticationFailureHandler();
//    }
}