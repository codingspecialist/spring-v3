package org.example.springv3.reply;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springv3.core.util.Resp;
import org.example.springv3.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;


@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final HttpSession session;
    private final ReplyService replyService;

    @DeleteMapping("/api/reply/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        // 1. 세션 체크(인증 체크) -> 주소로 처리 가능 (클리어)

        //2. Service 로직을 호출 - 댓글삭제
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글삭제(id, sessionUser);

        //3.응답
        //상태 코드 넣을려고  ResponseEntity.ok 내부적으로 new 한다.
        return ResponseEntity.ok(Resp.ok(null));

    }

    @PostMapping("/api/reply")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO saveDTO){
        System.out.println(1);
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(2);
        ReplyResponse.DTO replyDTO = replyService.댓글쓰기(saveDTO, sessionUser);
        System.out.println(7);
        return ResponseEntity.ok(Resp.ok(replyDTO));
    }

}
