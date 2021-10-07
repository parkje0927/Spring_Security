package study.springsecurity.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/member/login").setViewName("login");
        registry.addViewController("/member/login/result").setViewName("loginSuccess");
        registry.addViewController("/member/logout/result").setViewName("logout");
        registry.addViewController("/member/denied").setViewName("denied");
        registry.addViewController("/member/info").setViewName("myInfo");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/member/signup").setViewName("signup");

    }
}
