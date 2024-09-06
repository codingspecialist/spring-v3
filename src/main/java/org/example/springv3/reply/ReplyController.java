package org.example.springv3.reply;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.core.error.ex.ExceptionApi403;
import org.example.springv3.core.util.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        // 1. 인증 체크 -> 주소처리
        // 2. 서비스 호출 - 댓글삭제
        // 3. 응답
        //return ResponseEntity.ok(Resp.ok(null));
        throw new ExceptionApi403("권한이 없습니다");
    }
}
