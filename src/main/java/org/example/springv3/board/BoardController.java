package org.example.springv3.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springv3.user.User;
import org.example.springv3.user.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;


    @GetMapping("/api/board/save-form")
    public String saveForm() {
        session.setAttribute("sessionUser", User.builder().id(1).username("ssar").password("1234").email("ssar@nate.com").build());
        //User sessionUser = (User) session.getAttribute("sessionUser");

        return "board/save-form";
    }


    @PostMapping("/api/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.게시글쓰기(saveDTO, sessionUser);

        return "redirect:/";

    }



    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = User.builder()
                .id(1).username("ssar").password("1234").email("ssar@nate.com").build();
        session.setAttribute("sessionUser", sessionUser);

        BoardResponse.DetailDTO model = boardService.게시물상세보기(sessionUser, id);
        request.setAttribute("model", model);

        return "board/detail";


    }

}
