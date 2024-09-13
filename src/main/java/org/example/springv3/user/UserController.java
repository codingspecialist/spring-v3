package org.example.springv3.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springv3.core.util.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/api/user/profile")
    public String profile(@RequestParam("profile") MultipartFile profile){
        User sessionUser = (User) session.getAttribute("sessionUser");
        userService.프로필업로드(profile, sessionUser);

        return "redirect:/api/user/profile-form";
    }

    @GetMapping("/api/user/profile-form")
    public String profileForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        String profile = userService.프로필사진가져오기(sessionUser);
        request.setAttribute("profile", profile);
        return "user/profile-form";
    }

    // http://localhost:8080/user/samecheck?username=hello
    @GetMapping("/user/samecheck")
    public ResponseEntity<?> sameCheck(@RequestParam("username") String username) {
        boolean isSameUsername = userService.유저네임중복되었니(username);
        return ResponseEntity.ok(Resp.ok(isSameUsername, isSameUsername ? "중복되었어요" : "중복되지않았어요"));
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO loginDTO, Errors errors) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO joinDTO, Errors errors) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }
}
