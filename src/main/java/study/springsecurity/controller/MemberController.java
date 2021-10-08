package study.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import study.springsecurity.dto.MemberDto;
import study.springsecurity.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService userService;

    //메인 페이지
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    //회원가입 페이지
    @GetMapping("/member/signup")
    public String dispSignup() {
        return "/signup";
    }

    //회원가입 처리
    @PostMapping("/member/signup")
    public String execSignup(MemberDto memberDto) {
        userService.save(memberDto);
        return "redirect:/member/login";
    }

    //로그인 페이지
    @GetMapping("/member/login")
    public String dispLogin() {
        return "/login";
    }

    //로그인 실패 페이지
    @PostMapping("/member/login")
    public String loginFailure(@RequestParam("error") String error, Model model) {
        System.out.println(model.getAttribute("username"));
        return "/login";
    }
    

    //로그인 결과 페이지
    @GetMapping("/member/login/result")
    public String dispLoginResult() {
        return "/loginSuccess";
    }

    //로그아웃 결과 페이지
    @GetMapping("/member/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    //접근 거부 페이지
    @GetMapping("/member/denied")
    public String dispDenied() {
        return "/denied";
    }

    //내 정보 페이지
    @GetMapping("/member/info")
    public String dispMyInfo() {
        return "/myInfo";
    }

    //어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
}
