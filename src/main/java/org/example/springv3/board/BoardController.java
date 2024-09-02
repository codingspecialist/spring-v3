package org.example.springv3.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springv3.user.UserService;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;
}
