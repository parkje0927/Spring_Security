package study.springsecurity.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Getter
@Setter
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String username;
    private String password;;
    private String errorMessage;
    private String defaultFailureUrl = "/login?error";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String loginUserName = request.getParameter(username);
        String loginPassword = request.getParameter(password);

        request.setAttribute(username, loginUserName);
        request.setAttribute(password, loginPassword);

        System.out.println("loginUserName = " + loginUserName);
        System.out.println("username = " + username);

//        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
    }
}
