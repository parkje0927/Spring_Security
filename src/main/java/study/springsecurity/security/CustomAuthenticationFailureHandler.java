package study.springsecurity.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Getter
@Setter
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_URL = "/member/login?error=true";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String loginUserName = request.getParameter("username");
        String loginPassword = request.getParameter("password");
        String errorMessage = null;

        errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("username", loginUserName);
        request.setAttribute("password", loginPassword);

        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);

//        if (exception instanceof BadCredentialsException || exception  instanceof InternalAuthenticationServiceException) {
//            errorMessage = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
//        } else if (exception instanceof DisabledException) {
//            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
//        } else if (exception instanceof CredentialsExpiredException) {
//            errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
//        } else {
//            errorMessage = "알 수 없는 이유로 로그인에 실행하였습니다. 관리자에게 문의하세요.";
//        }
//
//        request.setAttribute("errorMessage", errorMessage);
//        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }
}
