package org.example.springv3.board;

import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springv3.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
=======
import lombok.RequiredArgsConstructor;
import org.example.springv3.user.UserService;
import org.springframework.stereotype.Controller;
>>>>>>> c3845b53949d35ae85331d9234a7e1c7be14e7ad

@RequiredArgsConstructor
@Controller
public class BoardController {
<<<<<<< HEAD

    private final HttpSession session;
    private final BoardService boardService;


    @PostMapping("/api/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.게시글수정(id, updateDTO, sessionUser);

        return "redirect:/board/" + id;
    }



=======
    private final HttpSession session;
    private final BoardService boardService;
>>>>>>> c3845b53949d35ae85331d9234a7e1c7be14e7ad
}
