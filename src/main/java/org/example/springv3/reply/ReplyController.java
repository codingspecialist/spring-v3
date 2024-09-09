package org.example.springv3.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.core.error.ex.ExceptionApi403;
import org.example.springv3.core.util.Resp;
import org.example.springv3.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final HttpSession session;
    private final ReplyService replyService;

    @PostMapping("/api/reply")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO saveDTO){
        System.out.println(1);
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(2);
        ReplyResponse.DTO replyDTO = replyService.댓글쓰기(saveDTO, sessionUser);
        System.out.println(7);
        return ResponseEntity.ok(Resp.ok(replyDTO));
    }

    @DeleteMapping("/api/reply/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글삭제(id, sessionUser);
        return ResponseEntity.ok(Resp.ok(null));
    }
}
