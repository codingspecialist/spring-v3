package org.example.springv3.board;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springv3.user.User;
import org.example.springv3.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;


    // subtitle=제목1&postContent=내용1
    @PostMapping("/api/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) { // 스프링 기본전략 = x-www-form-urlencoded 파싱
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 문법 발동 : DTO 앞에 @Valid 붙이면 DTO가 생성 시 앞에 anotation 분석해서 null이나 공백이면 Errors로 객체를 다 넘김

        // 인증 체크 필요
        boardService.게시글작성(saveDTO, sessionUser);
        return "redirect:/";
    }


    @GetMapping("/board/save-form")
    public String saveForm(Model model) {
        return "board/save-form";
    }



}
